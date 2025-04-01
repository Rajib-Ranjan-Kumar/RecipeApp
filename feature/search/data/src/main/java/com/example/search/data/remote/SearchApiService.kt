package com.example.search.data.remote

import com.example.search.data.model.ReceipeDetailsResponse
import com.example.search.data.model.ReceipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {
    //https://www.themealdb.com/api/json/v1/1/search.php?s=Arrabiata
    @GET("/api/json/v1/1/search.php?")
    suspend fun getReceipes(
        @Query("s") search: String
    ): Response<ReceipeResponse>

    //https://www.themealdb.com/api/json/v1/1/lookup.php?i=52772
    @GET("api/json/v1/1/lookup.php?")
    suspend fun getReceipeDetails(@Query("i")id:String):Response<ReceipeDetailsResponse>
}