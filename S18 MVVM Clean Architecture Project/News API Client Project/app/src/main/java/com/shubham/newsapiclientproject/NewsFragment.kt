package com.shubham.newsapiclientproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shubham.newsapiclientproject.data.util.Resource
import com.shubham.newsapiclientproject.databinding.FragmentNewsBinding
import com.shubham.newsapiclientproject.presentation.adapter.NewsAdapter
import com.shubham.newsapiclientproject.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Suppress("MoveLambdaOutsideParentheses")
class NewsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter

    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0
    private var page = 1
    private var country = "us"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Why we have overridden this method? because it get called immediately after views are created.

        viewModel = (activity as MainActivity).viewModel
        newsAdapter = (activity as MainActivity).newsAdapter
        binding = FragmentNewsBinding.bind(view)

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

        viewModel.getNewsHeadlines(country, page)

        viewModel.newsHeadlines.observe(viewLifecycleOwner, { response ->

            when(response) {

                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles.toList())

                        if(it.totalResults%20 == 0) {
                            pages = it.totalResults / 20
                        } else {
                            pages = it.totalResults / 20 + 1
                        }

                        isLastPage = page == pages
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }

                is Resource.Error -> {
                    hideProgressBar()

                    response.message?.let {
                        Toast.makeText(activity, "An error occured: $it", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    private fun initRecyclerView() {

//        newsAdapter = NewsAdapter()

        binding.recyclerViewNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NewsFragment.onScrollListener)
        }
    }

    private fun showProgressBar() {

        isLoading = true

        binding.apply{
            progressBarNews.visibility = View.VISIBLE
            recyclerViewNews.visibility = View.GONE
        }
    }

    private fun hideProgressBar() {

        isLoading = false

        binding.apply{
            progressBarNews.visibility = View.GONE
            recyclerViewNews.visibility = View.VISIBLE
        }
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = binding.recyclerViewNews.layoutManager as LinearLayoutManager

            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition + visibleItems >= sizeOfTheCurrentList
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling

            Log.d("PHOTO_LOG", "onScrolled..")

            if(shouldPaginate) {
                page++
                viewModel.getNewsHeadlines(country, page)
                isScrolling = false
            }

        }
    }


    // Search

    private fun setSearchView() {

        binding.searchViewNews.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // will be invoked when use types and hits enter button of keyboard

                viewModel.searchNews(country, query.toString(), page)
                viewSearchedNews()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // This will invoke on every text change inside search view.

                // Main scope is coroutine launcher specially created for user interface components.
                MainScope().launch {
                    delay(2000)
                    viewModel.searchNews(country, newText.toString(), page)
                    viewSearchedNews()
                }
                return false
            }
        })

        binding.searchViewNews.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                initRecyclerView()
                viewNewsList()
                return false
            }
        })
    }

    fun viewSearchedNews() {

        viewModel.searchedNews.observe(viewLifecycleOwner, { response ->

            when(response) {

                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles.toList())

                        if(it.totalResults%20 == 0) {
                            pages = it.totalResults / 20
                        } else {
                            pages = it.totalResults / 20 + 1
                        }

                        isLastPage = page == pages
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }

                is Resource.Error -> {
                    hideProgressBar()

                    response.message?.let {
                        Toast.makeText(activity, "An error occured: $it", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

}