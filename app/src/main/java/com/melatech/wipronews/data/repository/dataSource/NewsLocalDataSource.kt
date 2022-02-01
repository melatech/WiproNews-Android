package com.melatech.wipronews.data.repository.dataSource

import com.melatech.wipronews.data.model.Article
import kotlinx.coroutines.flow.Flow

/**
 *created by Jason Junior Calvert on 31/01/2022.
 */
interface NewsLocalDataSource {

    suspend fun saveArticleToDB_lds(article: Article)

    fun getSavedArticles_lds(): Flow<List<Article>>

    suspend fun deleteArticlesFromDB_lds(article: Article)
}