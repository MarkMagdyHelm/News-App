package com.Mark.news.commons.data.remote



import com.example.mostpopularapp.ui.mplist.models.MostPopularResponseObj
import io.reactivex.Single
import retrofit2.http.*

interface NewsService {

    @GET()
    fun getMostPopuler(@Url url: String?, @Query("api-key") query: String?): Single<MostPopularResponseObj>

}