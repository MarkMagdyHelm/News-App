package com.Mark.news.vacation.vacationlists.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.Mark.news.vacation.vacationlists.model.NewsDataContract
import io.reactivex.disposables.CompositeDisposable

@Suppress("UNCHECKED_CAST")
class NewsViewModelFactory(private val repository: NewsDataContract.Repository, private val compositeDisposable: CompositeDisposable) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(repository, compositeDisposable) as T
    }
}