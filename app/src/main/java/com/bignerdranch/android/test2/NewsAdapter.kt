package com.bignerdranch.android.test2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.test2.databinding.NewsItemBinding

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsHolder>(){

    val newsList = ArrayList<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsHolder(view)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.bind((newsList[position]))
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class NewsHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = NewsItemBinding.bind(item)

        fun bind(news: News) = with(binding){
            title.text = "title"
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun addNews(news: News){
        newsList.add(news)
        //notifyDataSetChanged()
    }
}