package com.shubham.newsapiclientproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shubham.newsapiclientproject.databinding.FragmentSavedNewsBinding
import com.shubham.newsapiclientproject.presentation.adapter.NewsAdapter
import com.shubham.newsapiclientproject.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.InternalCoroutinesApi


class SavedNewsFragment : Fragment() {

    private lateinit var fragmentSavedBinding: FragmentSavedNewsBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_news, container, false)
    }

    @OptIn(InternalCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentSavedBinding = FragmentSavedNewsBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        newsAdapter = (activity as MainActivity).newsAdapter

        newsAdapter.setOnItemClickListener {

            val bundle = Bundle().apply {
                putSerializable("selected_article", it)
            }

            findNavController().navigate(
                R.id.action_savedNewsFragment_to_infoFragment,
                bundle
            )
        }

        initRecyclerView()

        viewModel.getSavedNews().observe(viewLifecycleOwner, {
            newsAdapter.differ.submitList(it)
        })

    }

    private fun initRecyclerView() {
        fragmentSavedBinding.recyclerViewSavedNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }



}