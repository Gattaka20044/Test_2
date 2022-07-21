package com.bignerdranch.android.test2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.test2.databinding.NewsFragmentBinding

private const val TAG = "NewsFragment"

class NewsFragment : Fragment() {

    lateinit var binding: NewsFragmentBinding
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.news_fragment, container, false)

        newsRecyclerView = view.findViewById(R.id.recyclerNews)
        newsRecyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel.newsItemLiveData.observe(
            viewLifecycleOwner,
            Observer { newsItem ->
                Log.d(TAG, "Have news items from view model $newsItem")
                newsRecyclerView.adapter = NewsAdapter(newsItem)
            }
        )
    }

    private class NewsHolder(itemTextView: TextView) : RecyclerView.ViewHolder(itemTextView) {
        val bindTitle: (CharSequence) -> Unit = itemTextView::setText
    }

    private class NewsAdapter(private val newsItem: List<NewsItem>) : RecyclerView.Adapter<NewsHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
            val textView = TextView(parent.context)
            return NewsHolder(textView)
        }

        override fun onBindViewHolder(holder: NewsHolder, position: Int) {
            val newsItem = newsItem[position]
            holder.bindTitle(newsItem.title)
        }

        override fun getItemCount(): Int = newsItem.size

    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}
/*
private fun init() {
    binding.apply {
        recyclerNews.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerNews.adapter = adapter
        for (i in 1..8) {
            val news = NewsItem("News $i")
            adapter.addNews(news)
        }
    }
}

 */