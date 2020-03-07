package com.Mark.news.vacation.vacationlists.di

import com.Mark.news.application.BaseApp
import com.Mark.news.vacation.vacationlists.di.component.DaggerNewsListComponent

import com.Mark.news.vacation.vacationlists.di.component.NewsListComponent
import javax.inject.Singleton

@Singleton
object NewsListDH {
    private var listComponent: NewsListComponent? = null

    fun listComponent(): NewsListComponent {
        if (listComponent == null)
            listComponent = DaggerNewsListComponent.builder().coreComponent(
                BaseApp
                .coreComponent).build()
        return listComponent as NewsListComponent
    }

    fun destroyListComponent() {
        listComponent = null
    }

}