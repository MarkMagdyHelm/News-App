
package com.Mark.news.ui.news.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.Mark.news.R
import com.Mark.news.application.BaseActivity
import com.Mark.news.ui.news.model.Pojo.Article
import com.Mark.news.vacation.vacationlists.adapter.Interaction
import com.Mark.news.vacation.vacationlists.adapter.NewsListAdapter
import com.Mark.news.vacation.vacationlists.di.NewsListDH
import com.Mark.news.vacation.vacationlists.viewmodel.NewsViewModel
import com.Mark.news.vacation.vacationlists.viewmodel.NewsViewModelFactory
import kotlinx.android.synthetic.main.activity_news_list.*
import javax.inject.Inject

class NewsListsActivity: BaseActivity(), View.OnClickListener,Interaction {


    private val component by lazy { NewsListDH.listComponent() }
    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory
    private val viewModel: NewsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(NewsViewModel::class.java)
    }

    @Inject
    lateinit var newsAdapter: NewsListAdapter

    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        component.inject(this)
        bindViewModel()

        newsAdapter.interaction = this
        rvPosts.adapter = newsAdapter
        rvPosts.layoutManager = LinearLayoutManager(this)


    }


    private fun bindViewModel() {
        progressBar.setVisibility(View.VISIBLE)
        viewModel.fetchNewsList(7)
        viewModel.newsCallBack().removeObservers(this)
        viewModel.newsCallBack().observe(this, Observer {
            initAdapter(it.articles!!)
        })

        //fetch data based on the type
    }

    private fun initAdapter(listItems: List<Article>) {
        //Update list with new list comes from server
        progressBar.setVisibility(View.GONE)
        newsAdapter?.swapData(listItems!!)
    }

    override fun onStart() {
        super.onStart()

//        button22.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when (v!!.id) {

        }


    }


    override fun itemsClicked(obj: Article) {
        val uri: Uri = Uri.parse( obj.url!!) // missing 'http://' will cause crashed
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

}
