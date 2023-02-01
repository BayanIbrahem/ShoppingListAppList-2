package com.example.shopping_list.data.remote

import com.example.shopping_list.domain.model.ImageResponse
import com.example.testing.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PexabayApi {
    @GET("/api/")
    suspend fun searchForImages(
        @Query("key") apiKey: String = BuildConfig.API_KEY,
        @Query("q") searchString: String,
    ): Response<ImageResponse>


}