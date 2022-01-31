package com.melatech.wipronews.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs

import com.melatech.wipronews.R
import com.melatech.wipronews.databinding.FragmentInfoBinding

/**
 *created by Jason Junior Calvert on 29/01/2022.
 */


class InfoFragment : Fragment() {

    private lateinit var fragmentInfoBinding: FragmentInfoBinding

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
        fragmentInfoBinding.wvInfo.apply {
            webViewClient = WebViewClient()
            if(article.url != ""){
                loadUrl(article.url)
            }

        }

    }
}