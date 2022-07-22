package com.bignerdranch.android.test2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.test2.api.NewsApi
import com.bignerdranch.android.test2.databinding.NewsFragmentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "NewsFragment"

class NewsFragment : Fragment() {

    private lateinit var binding: NewsFragmentBinding

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel.newsItemViewModel.observe(
            viewLifecycleOwner,
            Observer {
                //Log.d(TAG, "Have gallery items from ViewModel $it")
                newsRecyclerView.adapter = NewsAdapter(it)
                // Обновить данные, поддерживающие представление утилизатора
            })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = NewsFragmentBinding.inflate(inflater)
        //val view = inflater.inflate(R.layout.news_fragment, container, false)

        newsRecyclerView = binding.recyclerNews
        newsRecyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    private class NewsHolder(itemTextView: TextView) : RecyclerView.ViewHolder(itemTextView) {
        val bindTitle: (CharSequence) -> Unit = itemTextView::setText
    }

    private class NewsAdapter(private val newsItems: List<NewsItem>) : RecyclerView.Adapter<NewsHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
            val textView = TextView(parent.context)
            return NewsHolder(textView)
        }

        override fun onBindViewHolder(holder: NewsHolder, position: Int) {
            val newsItem = newsItems[position]
            holder.bindTitle(newsItem.title)
        }

        override fun getItemCount(): Int = newsItems.size

    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}