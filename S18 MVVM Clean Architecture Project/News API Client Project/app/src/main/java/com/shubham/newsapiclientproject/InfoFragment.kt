package com.shubham.newsapiclientproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.shubham.newsapiclientproject.databinding.FragmentInfoBinding
import com.shubham.newsapiclientproject.presentation.viewmodel.NewsViewModel


class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentInfoBinding.bind(view)

        val args : InfoFragmentArgs by navArgs()
        val article = args.selectedArticle

        viewModel = (activity as MainActivity).viewModel

        binding.webViewInfo.apply {

            webViewClient = WebViewClient()

            if(article.url != null) {
                loadUrl(article.url)
            }
        }

        binding.fabSave.setOnClickListener {

            viewModel.saveArticle(article)

            Snackbar.make(view, "Saved Successfully!", Snackbar.LENGTH_LONG).show()
        }

    }


}