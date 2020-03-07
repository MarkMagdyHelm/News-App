package com.Mark.news.news.model.Pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class NewsResponseObj {

        @SerializedName("status")
        @Expose
        var status: String? = null

        @SerializedName("totalResults")
        @Expose
        var totalResults: Int? = null

        @SerializedName("articles")
        @Expose
        var articles: List<Article>? = null

    }