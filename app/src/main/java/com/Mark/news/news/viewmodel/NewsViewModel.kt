package com.Mark.news.vacation.vacationlists.viewmodel


import androidx.lifecycle.ViewModel
import com.Mark.news.news.model.Pojo.NewsResponseObj
import com.Mark.news.utils.SingleLiveEvent
import com.Mark.news.vacation.vacationlists.model.NewsDataContract

import io.reactivex.disposables.CompositeDisposable


class NewsViewModel(
    private val repo: NewsDataContract.Repository,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {
//
    fun fetchNewsList(num : Int)
    {
        return repo.getNewsList(num)
    }

    fun newsCallBack(): SingleLiveEvent<NewsResponseObj>
    {
        return repo.newsCallBacks
    }


    override fun onCleared() {
        super.onCleared()
        //clear the disposables when the com.Mark.mostpopuler.home.viewmodel is cleared
        compositeDisposable.clear()
//        PostDH.destroyListComponent()
    }

}