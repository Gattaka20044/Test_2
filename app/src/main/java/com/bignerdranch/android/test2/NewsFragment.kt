package com.bignerdranch.android.test2

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.test2.api.NewsApi
import com.bignerdranch.android.test2.databinding.NewsFragmentBinding
import com.bignerdranch.android.test2.databinding.NewsItemBinding
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
    private lateinit var thumbnailDownloader: ThumbnailDownloader<NewsHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        val responseHandler = Handler()
        thumbnailDownloader = ThumbnailDownloader(responseHandler) { newsHolder, bitmap ->
            val drawable = BitmapDrawable(resources, bitmap)
            newsHolder.bindingClass.imageView.setImageDrawable(drawable)
        }
        lifecycle.addObserver(thumbnailDownloader)
    }

    override fun onDestroy() {
        super.onDestroy()

        lifecycle.removeObserver(thumbnailDownloader)
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

    private class NewsHolder(item: View) : RecyclerView.ViewHolder(item) {
        val bindingClass = NewsItemBinding.bind(item)
        fun bind(news: NewsItem) = with(bindingClass) {
            title.text = news.title
            author.text = news.author
            data.text = news.data
            description.text = news.description
        }
        //val bindTitle: (CharSequence) -> Unit = itemTextView::setText
    }

    private inner class NewsAdapter(private val newsItems: List<NewsItem>) : RecyclerView.Adapter<NewsHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
            //val textView = TextView(parent.context)
            return NewsHolder(view)
        }

        override fun onBindViewHolder(holder: NewsHolder, position: Int) {
            holder.bind(newsItems[position])

            thumbnailDownloader.queueThumbnail(holder, newsItems[position].urlToImage)
        //val newsItem = newsItems[position]
           // holder.bindTitle(newsItem.title)
        }

        override fun getItemCount(): Int = newsItems.size

    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}