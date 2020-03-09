package com.Mark.news.ui.news.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.Mark.news.R
import com.Mark.news.application.BaseActivity
import com.Mark.news.constants.Constants

import com.Mark.news.ui.news.model.Pojo.Article
import com.Mark.news.ui.settings.SettingActivity
import com.Mark.news.ui.settings.SettingsObj
import com.Mark.news.vacation.vacationlists.adapter.Interaction
import com.Mark.news.vacation.vacationlists.adapter.NewsListAdapter
import com.Mark.news.vacation.vacationlists.di.NewsListDH
import com.Mark.news.vacation.vacationlists.viewmodel.NewsViewModel
import com.Mark.news.vacation.vacationlists.viewmodel.NewsViewModelFactory
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_news_list.*
import java.nio.charset.StandardCharsets
import javax.inject.Inject

class NewsListsActivity : BaseActivity(), View.OnClickListener, Interaction {

    var settingsObjs = SettingsObj()
    var categories: String? = ""
    var query: String? = ""
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
        //get settingsObj
        settingsObjs = getSettingsObj()
        initListener()
        //calling api and observing on result
        bindViewModel()
        initRecycler()
    }

    private fun initListener() {
        settings_iv.setOnClickListener(this)
        search_et.addTextChangedListener(generalTextWatcher)
    }

    private fun initRecycler() {
        newsAdapter.interaction = this
        rvPosts.adapter = newsAdapter
        rvPosts.layoutManager = LinearLayoutManager(this)
    }
    private fun bindViewModel() {


        if (isConnectedToNetwork()) {
            progressBar.setVisibility(View.VISIBLE)
            viewModel.fetchNewsList(
                settingsObjs.lang!!,
                preparCategories(settingsObjs.categories!!),
                settingsObjs.sortingBy!!,
                query!!
            )
            viewModel.newsCallBack().removeObservers(this)
            viewModel.newsFailerCallBack().removeObservers(this)
            viewModel.newsCallBack().observe(this, Observer { initAdapter(it.articles!!) })
        } else
            Toast.makeText(this, getString(R.string.network_check), Toast.LENGTH_LONG).show()
    }

    private fun initAdapter(listItems: List<Article>) {
        //Update list with new list comes from server
        progressBar.setVisibility(View.GONE)
        newsAdapter.swapData(listItems)
    }

    override fun onClick(v: View?) {

        when (v!!.id) {
            R.id.settings_iv -> {
                val intent = Intent(this, SettingActivity::class.java)
                intent.putExtra("comeFrom", 1)
                startActivity(intent)
                finish()
            }
        }
    }

    fun preparCategories(list: ArrayList<String>): String {
        for (i in list)
            categories = categories + i + ","
        return categories!!.dropLast(1)
    }

    override fun itemsClicked(item: Article) {
        val uri: Uri = Uri.parse(item.url!!) // missing 'http://' will cause crashed
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun getSettingsObj(): SettingsObj {
        val gson = Gson()
        val user = PreferenceManager.getDefaultSharedPreferences(this)
            .getString(Constants.USER_SETTINGS, "empty")
        return gson.fromJson(user, SettingsObj::class.java)
    }

    //handling search on textwatcher
    private val generalTextWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(
            s: CharSequence, start: Int, before: Int,
            count: Int
        ) {
        }

        override fun afterTextChanged(s: Editable) {
            if (search_et.getText().hashCode() == s.hashCode()) {
                query = String(
                    search_et.getText().toString().toByteArray(),
                    StandardCharsets.UTF_8
                )
                progressBar.visibility = View.VISIBLE
                viewModel.fetchNewsList(
                    settingsObjs.lang!!,
                    preparCategories(settingsObjs.categories!!),
                    settingsObjs.sortingBy!!,
                    query!!
                )

                //                mNewsModel.getNewsFromServerByName(query);
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    }

    fun isConnectedToNetwork(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting() ?: false

    }
}
