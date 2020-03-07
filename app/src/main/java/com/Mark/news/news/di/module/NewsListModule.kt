package com.Mark.news.vacation.vacationlists.di.module

import com.Mark.news.vacation.vacationlists.adapter.NewsListAdapter
import com.Mark.news.commons.data.remote.NewsService
import com.Mark.news.commons.networking.Scheduler
import com.Mark.news.vacation.vacationlists.di.NewsListScope
import com.Mark.news.vacation.vacationlists.model.NewsDataContract
import com.Mark.news.vacation.vacationlists.model.NewsRemoteData
import com.Mark.news.vacation.vacationlists.model.NewsRepository
import com.Mark.news.vacation.vacationlists.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

@Module
@NewsListScope
class NewsListModule {



    /*Adapter*/
    @Provides
    @NewsListScope
    fun provideadapter(): NewsListAdapter = NewsListAdapter()

    /*ViewModel*/
    @Provides
    @NewsListScope
    fun providelistViewModelFactory(repository: NewsDataContract.Repository, compositeDisposable: CompositeDisposable): NewsViewModelFactory = NewsViewModelFactory(repository,compositeDisposable)

    /*Repository*/
    @Provides
    @NewsListScope
    fun providelistRepo(remote: NewsDataContract.Remote, scheduler: Scheduler, compositeDisposable: CompositeDisposable): NewsDataContract.Repository = NewsRepository( remote, scheduler, compositeDisposable)

    @Provides
    @NewsListScope
    fun provideremoteData(postService: NewsService): NewsDataContract.Remote = NewsRemoteData(postService)

    @Provides
    @NewsListScope
    fun providecompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @NewsListScope
    fun providepostService(retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)
}