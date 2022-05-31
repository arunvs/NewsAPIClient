package com.fairfaxmedia.newsapiclient

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.fairfaxmedia.newsapiclient.databinding.FragmentInfoBinding
import com.fairfaxmedia.newsapiclient.presentation.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class InfoFragment : Fragment() {
    private lateinit var fragmentInfoBinding: FragmentInfoBinding
    private lateinit var viewModel : NewsViewModel

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
        val args : InfoFragmentArgs by navArgs()
        val article = args.selectedArticle

        viewModel=(activity as MainActivity).viewModel

        fragmentInfoBinding.wvInfo.apply {
           webViewClient = object : WebViewClient() {

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                   showProgressBar()
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    hideProgressBar()
                }

                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    view.loadUrl(url)
                    return true
                }
            }

            //val url = article.url.replace("http://","https://")
            loadUrl(article.url)

        }

        fragmentInfoBinding.fabSave.setOnClickListener {
           viewModel.saveArticle(article)
           Snackbar.make(view,"Saved Successfully!",Snackbar.LENGTH_LONG).show()
        }
    }

    private fun showProgressBar(){

        fragmentInfoBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){

        fragmentInfoBinding.progressBar.visibility = View.GONE
    }
}







