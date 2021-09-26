package com.example.github_search_app.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory








class GitHub_Instance {

    companion object {
        const val BaseURL = "https://api.github.com/search/"
        fun getRetrofitInstance(): Retrofit {


            return Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}