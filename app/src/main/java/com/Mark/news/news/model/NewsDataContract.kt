package com.Mark.news.vacation.vacationlists.model

import com.Mark.news.news.model.Pojo.NewsResponseObj
import com.Mark.news.utils.SingleLiveEvent
import io.reactivex.Single


interface NewsDataContract {

    interface Repository {
        val newsFailerCallBacks : SingleLiveEvent<String>
        val newsCallBacks : SingleLiveEvent<NewsResponseObj>
        fun getNewsList(num: Int)
    }

    interface Remote {
        fun getNewsList(num: Int): Single<NewsResponseObj>


    }
}