package com.example.github_search_app.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github_search_app.model.GitData
import com.example.github_search_app.network.GitHub_Api
import com.example.github_search_app.network.GitHub_Instance
import com.example.github_search_app.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Main_ViewModel: ViewModel() {

    var recyclerListLiveData: MutableLiveData<GitData> = MutableLiveData()
    var page: MutableLiveData<Int> = MutableLiveData()


    fun initSearch () {
        if (!CHECK) {
            QUERY = LIST_ALL
            QUERY2 = IS_BLANK
            CHECK=true
        }
    }

    fun nextPage() {
        PAGE++
        page.value = PAGE
    }

    fun prevPage() {
        if (PAGE==1) {PAGE=1}
        else{PAGE--}
        page.value = PAGE
    }

    fun getRecyclerListObserver(): MutableLiveData<GitData> {
        return recyclerListLiveData
    }


    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = GitHub_Instance.getRetrofitInstance().create(GitHub_Api::class.java)
            val response = retroInstance.search(QUERY, QUERY2, PAGE)
            recyclerListLiveData.postValue(response)
        }
    }

    fun resetPage() {
        PAGE = 1
    }
}