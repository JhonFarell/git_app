package com.example.github_search_app.network

import com.example.github_search_app.model.GitData
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHub_Api {

    @GET("repositories")
    suspend fun search(@Query ("q") query: String?,
                       @Query ("option2") query2: String?,
                       @Query ("page") page: Int
                       ): GitData
}