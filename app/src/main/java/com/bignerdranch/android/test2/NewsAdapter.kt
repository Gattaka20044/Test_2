package com.bignerdranch.android.test2

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.test2.databinding.NewsItemBinding

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class NewsHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = NewsItemBinding.bind(item)

        fun bind(news: News) = with(binding){
            title.text = "title"
        }

    }
}