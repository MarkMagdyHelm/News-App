package com.Mark.news.vacation.vacationlists.di.component

import com.Mark.news.commons.data.remote.NewsService
import com.Mark.news.commons.di.component.CoreComponent
import com.Mark.news.commons.networking.Scheduler
import com.Mark.news.news.view.NewsListsActivity
import com.Mark.news.vacation.vacationlists.di.NewsListScope
import com.Mark.news.vacation.vacationlists.di.module.NewsListModule

import dagger.Component

@NewsListScope
@Component(dependencies = [CoreComponent::class], modules = [NewsListModule::class])
interface NewsListComponent {

    //Expose to dependent components

    fun getNews(): NewsService

    fun scheduler(): Scheduler

    fun inject(newsRequest: NewsListsActivity)

}

