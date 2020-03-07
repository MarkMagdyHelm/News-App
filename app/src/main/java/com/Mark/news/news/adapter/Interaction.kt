package com.Mark.news.vacation.vacationlists.adapter

import com.Mark.news.news.model.Pojo.Article



interface Interaction {
    fun itemsClicked(
        item : Article
    )
}
