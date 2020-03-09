package com.Mark.news.vacation.vacationlists.model


import com.Mark.news.commons.data.remote.NewsService
import com.Mark.news.constants.Constants
import com.Mark.news.ui.news.model.Pojo.NewsResponseObj
import io.reactivex.Single


class NewsRemoteData(private val newsService: NewsService) : NewsDataContract.Remote {

    override fun getNewsList(country: String ,categories: String,sortType: String,searchTxt : String): Single<NewsResponseObj> {
        return newsService.getMostPopuler(Constants.API_URL+Constants.CONTROLER,country,categories,sortType,searchTxt,Constants.API_KEY)
    }

    }
