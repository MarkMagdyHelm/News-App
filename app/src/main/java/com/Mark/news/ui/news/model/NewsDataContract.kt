package com.Mark.news.vacation.vacationlists.model

import com.Mark.news.ui.news.model.Pojo.NewsResponseObj
import com.Mark.news.utils.SingleLiveEvent
import io.reactivex.Single


interface NewsDataContract {

    interface Repository {
        val newsFailerCallBacks : SingleLiveEvent<String>
        val newsCallBacks : SingleLiveEvent<NewsResponseObj>
        fun getNewsList(country: String ,categories: String,sortType: String,searchTxt : String)
    }

    interface Remote {
        fun getNewsList(country: String ,categories: String,sortType: String,searchTxt : String): Single<NewsResponseObj>


    }
}