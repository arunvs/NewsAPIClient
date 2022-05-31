package com.fairfaxmedia.newsapiclient.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fairfaxmedia.newsapiclient.data.model.Asset
import com.fairfaxmedia.newsapiclient.databinding.NewsListItemBinding

class NewsAdapter:RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    private val callback = object : DiffUtil.ItemCallback<Asset>(){
        override fun areItemsTheSame(oldItem: Asset, newItem: Asset): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Asset, newItem: Asset): Boolean {
           return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,callback)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsListItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding)
    }



    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class NewsViewHolder(
        private val binding:NewsListItemBinding):
        RecyclerView.ViewHolder(binding.root){
           fun bind(article: Asset){
               binding.headline.text = article.headline
               binding.description.text = article.theAbstract
               binding.byLine.text = "~By "+article.byLine

               Glide.with(binding.imageview.context).
               load(getSmallestImage(article)).
               into(binding.imageview)

               binding.root.setOnClickListener {
                  onItemClickListener?.let {
                        it(article)
                  }
               }
           }
        }

    private fun getSmallestImage(article: Asset): String {
        article.relatedImages.sortedBy { it.width }
        var images = article.relatedImages.sortedWith(compareBy { it.width*it.height })
        return images[0].url
    }


    private var onItemClickListener :((Asset)->Unit)?=null

        fun setOnItemClickListener(listener : (Asset)->Unit){
            onItemClickListener = listener
        }

}









