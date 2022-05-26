package com.toyibnurseha.news_mvvm.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.toyibnurseha.news_mvvm.models.Article
import com.toyibnurseha.news_mvvm.models.Source

@Dao
interface ArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article) : Long

    @Query("SELECT * FROM articles")
    fun getAllArticles() : LiveData<List<Article>>

    @Query("SELECT * FROM articles WHERE url=:url")
    fun getIsArticleSaved(url: String) : LiveData<Article>

    @Delete
    suspend fun  deleteArticle(article: Article)
}