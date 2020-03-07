package com.Mark.news.commons.data.remote




import com.Mark.news.news.model.Pojo.NewsResponseObj
import io.reactivex.Single
import retrofit2.http.*

interface NewsService {

    @GET()
    fun getMostPopuler(@Url url: String?, @Query("country") country: String?,@Query("apiKey") query: String?): Single<NewsResponseObj>

}