package com.Mark.news.ui.settings

import com.Mark.news.ui.news.model.Pojo.Article
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SettingsObj (
    var lang: String? = null,
    var categories: ArrayList<String>? = null)