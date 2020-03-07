package com.Mark.news.news.model.Pojo
import com.google.gson.annotations.SerializedName

class Source {
    @SerializedName("format")
    var format: String? = null
    @SerializedName("height")
    var height: Long? = null
    @SerializedName("url")
    var url: String? = null
    @SerializedName("width")
    var width: Long? = null

}