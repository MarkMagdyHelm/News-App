package com.Mark.news.news.model.Pojo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Source {

    @SerializedName("name")
    @Expose
    var name: String? = null
}