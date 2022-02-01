package com.melatech.wipronews.data.db

import androidx.room.*
import com.melatech.wipronews.data.model.Article
import kotlinx.coroutines.flow.Flow

/**
 *created by Jason Junior Calvert on 31/01/2022.
 */
@Dao
interface ArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //suspend fun insert_dao(article: Article): Long
    suspend fun insert_dao(article: Article)

    @Query("SELECT * FROM articles")
    fun getAllArticles_dao(): Flow<List<Article>>

    @Delete
    suspend fun deleteArticles(article: Article)
}