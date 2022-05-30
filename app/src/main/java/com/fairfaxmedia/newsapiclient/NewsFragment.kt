package com.fairfaxmedia.newsapiclient

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fairfaxmedia.newsapiclient.databinding.FragmentNewsBinding
import com.fairfaxmedia.newsapiclient.presentation.adapter.NewsAdapter
import com.fairfaxmedia.newsapiclient.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class NewsFragment : Fragment() {
    private  lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var fragmentNewsBinding: FragmentNewsBinding
    private var isLoading = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNewsBinding = FragmentNewsBinding.bind(view)
        viewModel= (activity as MainActivity).viewModel
        newsAdapter= (activity as MainActivity).newsAdapter
        newsAdapter.setOnItemClickListener {
          val bundle = Bundle().apply {
             putSerializable("selected_article",it)
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

        viewModel.getNewsHeadLines()
        viewModel.newsHeadLines.observe(viewLifecycleOwner) { response ->
            when (response) {
                is com.fairfaxmedia.newsapiclient.data.util.Resource.Success -> {

                    hideProgressBar()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.assets.toList())
                    }
                }
                is com.fairfaxmedia.newsapiclient.data.util.Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                is com.fairfaxmedia.newsapiclient.data.util.Resource.Loading -> {
                    showProgressBar()
                }

            }
        }
    }

    private fun initRecyclerView() {
        fragmentNewsBinding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }

    private fun showProgressBar(){
        isLoading = true
        fragmentNewsBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        isLoading = false
        fragmentNewsBinding.progressBar.visibility = View.GONE
    }


   //search
   private fun setSearchView(){
     fragmentNewsBinding.svNews.setOnQueryTextListener(
         object : SearchView.OnQueryTextListener{
             override fun onQueryTextSubmit(p0: String?): Boolean {
                 viewModel.searchNews(p0.toString())
                 viewSearchedNews()
                 return false
             }

             override fun onQueryTextChange(p0: String?): Boolean {
                 MainScope().launch {
                     delay(2000)
                     viewModel.searchNews( p0.toString())
                    // viewSearchedNews()
                 }
                 return false
             }

         })

         fragmentNewsBinding.svNews.setOnCloseListener {
             initRecyclerView()
             viewNewsList()
             false
         }
   }




   fun viewSearchedNews(){
       viewModel.searchedNews.observe(viewLifecycleOwner) { response ->
           when (response) {
               is com.fairfaxmedia.newsapiclient.data.util.Resource.Success -> {

                   hideProgressBar()
                   response.data?.let { it ->
                       Log.i("MYTAG", "came here ${it.assets.toList().size}")
                       val newsItems = it.assets.toList().sortedBy { it.timeStamp }
                       newsAdapter.differ.submitList(newsItems)
                   }
               }
               is com.fairfaxmedia.newsapiclient.data.util.Resource.Error -> {
                   hideProgressBar()
                   response.message?.let {
                       Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_LONG).show()
                   }
               }

               is com.fairfaxmedia.newsapiclient.data.util.Resource.Loading -> {
                   showProgressBar()
               }

           }
       }
   }

}
















