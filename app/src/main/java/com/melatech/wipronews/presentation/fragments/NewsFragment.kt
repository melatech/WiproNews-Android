package com.melatech.wipronews.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Adapter
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.melatech.wipronews.R
import com.melatech.wipronews.data.util.Resource
import com.melatech.wipronews.databinding.FragmentNewsBinding
import com.melatech.wipronews.presentation.activities.MainActivity
import com.melatech.wipronews.presentation.adapter.NewsAdapter
import com.melatech.wipronews.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *created by Jason Junior Calvert on 29/01/2022.
 */

class NewsFragment : Fragment() {
    val TAG = "NewsFragment"
    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    private var country = "us"
    private var page = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    //This function get called when all the views are created to prevent errors
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        //get viewModel from activity
        viewModel = (activity as MainActivity).viewModel
        newsAdapter = (activity as MainActivity).newsAdapter
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_article", it)
            }
            findNavController().navigate(
                R.id.action_newsFragment_to_infoFragment,
                bundle
            )
        }
        initRecyclerView()
        viewNewsList()
        setSearchView()
    }

    private fun viewNewsList() {
        viewModel.getNewsHeadLines_v(country, page)
        viewModel.newsHeadLines_m.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        Log.e(TAG, "came here ${it.articles.toList().size}")
                        newsAdapter.differ.submitList(it.articles.toList())
                        if (it.totalResults % 20 == 0){
                            pages = it.totalResults / 20
                        }else{
                            //pages++
                           pages = it.totalResults / 20 + 1
                        }
                        isLastPage = page == pages

                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An error has occurred: $it", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun initRecyclerView() {
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NewsFragment.onScrollListener)
        }
    }
    private fun showProgressBar(){
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        isLoading = false
        binding.progressBar.visibility = View.GONE
    }

    private val onScrollListener = object: RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                isScrolling = true
        }
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.rvNews.layoutManager as LinearLayoutManager
            val sizeOfCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            Log.e(TAG, "sizeOfCurrentList: ${sizeOfCurrentList}")
            Log.e(TAG, "visibleItems: ${visibleItems}")
            Log.e(TAG, "topPosition: ${topPosition}")
            Log.e(TAG, "page: ${page}")
            Log.e(TAG, "pages: ${pages}")
            //conditions to be satisfied before we can do pagination
            //1.on scrolling the currentList must reach last item
            val hasReachedToEnd = topPosition + visibleItems >= sizeOfCurrentList
            Log.e(TAG, "hasReachedToEnd:${hasReachedToEnd}")
            //2.if data is loading we should not paginate
            //3.if this is the last page we should not paginate
            //4.the list must have reached to the end
            //5.user must still be scrolling
            //when pagination happens page number should increase by 1
            val weShouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling
            Log.e(TAG, "weShouldPaginate: ${weShouldPaginate}")
            if (weShouldPaginate){
                page++
                viewModel.getNewsHeadLines_v(country, page)
                isScrolling = false

            }
        }
    }

    //search
    private fun setSearchView(){
        binding.svNews.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                viewModel.searchNews_v("us", p0.toString(), page)
                viewSearchedNews()
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                MainScope().launch {
                    delay(1000L)
                    viewModel.searchNews_v("us", p0.toString(), page)
                    viewSearchedNews()
                }
                return false
            }
        })
        binding.svNews.setOnCloseListener(object : SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                initRecyclerView()
                viewNewsList()
                return false
            }
        })
    }

    fun viewSearchedNews(){
       // viewModel.searchedNews_m(country, page)
        viewModel.searchedNews_m.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        Log.e(TAG, "came here ${it.articles.toList().size}")
                        newsAdapter.differ.submitList(it.articles.toList())
                        if (it.totalResults % 20 == 0){
                            pages = it.totalResults / 20
                        }else{
                            //pages++
                            pages = it.totalResults / 20 + 1
                        }
                        isLastPage = page == pages
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An error has occurred: $it", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }
}