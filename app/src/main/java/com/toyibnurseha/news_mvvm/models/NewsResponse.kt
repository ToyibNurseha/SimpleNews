package com.toyibnurseha.news_mvvm.models

import com.toyibnurseha.news_mvvm.models.Article

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)