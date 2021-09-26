package com.example.github_search_app.model

data class GitData(
    var total_Count: Int,
    var items: ArrayList<Item>
)

data class Item(
    var name: String,
    var owner: Owner,
    var private: Boolean
)

data class Owner (
    var login: String,
    var avatar_url: String
        )
