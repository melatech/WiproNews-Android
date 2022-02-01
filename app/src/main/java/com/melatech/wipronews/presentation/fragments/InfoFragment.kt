package com.melatech.wipronews.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar

import com.melatech.wipronews.R
import com.melatech.wipronews.databinding.FragmentInfoBinding
import com.melatech.wipronews.presentation.activities.MainActivity
import com.melatech.wipronews.presentation.viewmodel.NewsViewModel

/**
 *created by Jason Junior Calvert on 29/01/2022.
 */


class InfoFragment : Fragment() {

    private lateinit var fragmentInfoBinding: FragmentInfoBinding
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentInfoBinding = FragmentInfoBinding.bind(view)

        val args: InfoFragmentArgs by navArgs()
        val article = args.selectedArticle

        viewModel = (activity as MainActivity).viewModel

        fragmentInfoBinding.wvInfo.apply {
            webViewClient = WebViewClient()
            if(article.url != null){
                loadUrl(article.url)
            }
        }
        fragmentInfoBinding.fabSave.setOnClickListener {
            viewModel.saveArticle_v(article)
            //return inserted row id
            Snackbar.make(view, "Saved Successfully!", Snackbar.LENGTH_LONG).show()

        }
    }
}