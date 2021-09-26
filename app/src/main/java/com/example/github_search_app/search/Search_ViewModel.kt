package com.example.github_search_app.search

import androidx.lifecycle.ViewModel
import com.example.github_search_app.utils.QUERY
import com.example.github_search_app.utils.QUERY2

class Search_ViewModel: ViewModel() {

    fun searchOption (queryIn: String, userIn: String) {
        QUERY = queryIn
        QUERY2 = userIn
    }
}