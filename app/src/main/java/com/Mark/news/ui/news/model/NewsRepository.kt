package com.Mark.news.vacation.vacationlists.model

import com.Mark.news.commons.extensions.addTo
import com.Mark.news.commons.extensions.performOnBackOutOnMain
import com.Mark.news.commons.networking.Scheduler
import com.Mark.news.ui.news.model.Pojo.NewsResponseObj
import com.Mark.news.utils.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable

class NewsRepository(

    val remote: NewsDataContract.Remote,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable

) : NewsDataContract.Repository {
    override val newsCallBacks: SingleLiveEvent<NewsResponseObj> = SingleLiveEvent()
    override val newsFailerCallBacks: SingleLiveEvent<String> = SingleLiveEvent()



    override fun getNewsList(country: String ,categories: String,sortType: String,searchTxt : String){
        remote.getNewsList(country,categories,sortType,searchTxt).performOnBackOutOnMain(scheduler)
            .subscribe({ res ->
                if (res != null) {
                    if (res.articles!!.size!! < 0) {
                        newsFailerCallBacks.postValue("error")
                        //post fail callback

                    } else {
                        if (res.articles!!.size!! > 0) {
                            //success
                            newsCallBacks.postValue(res)

                        } else {
                            //fail
                            newsFailerCallBacks.postValue("error")

                        }
                    }
                } else {
                    //fail
                    newsFailerCallBacks.postValue("Login failed")

                }

            },
                {


                })
            .addTo(compositeDisposable)


    }

}