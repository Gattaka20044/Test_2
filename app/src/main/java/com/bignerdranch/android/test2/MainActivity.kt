package com.bignerdranch.android.test2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.test2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, NewsFragment.newInstance())
                .commit()
        }

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