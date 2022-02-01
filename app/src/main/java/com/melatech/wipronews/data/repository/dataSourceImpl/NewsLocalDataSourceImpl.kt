package com.melatech.wipronews.data.repository.dataSourceImpl

import com.melatech.wipronews.data.db.ArticleDAO
import com.melatech.wipronews.data.model.Article
import com.melatech.wipronews.data.repository.dataSource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

/**
 *created by Jason Junior Calvert on 31/01/2022.
 */
class NewsLocalDataSourceImpl(
    private val articleDAO: ArticleDAO
    ): NewsLocalDataSource {
    override suspend fun saveArticleToDB_lds(article: Article) {
        return articleDAO.insert_dao(article)
    }

    override fun getSavedArticles_lds(): Flow<List<Article>> {
        return articleDAO.getAllArticles_dao()
    }

    override suspend fun deleteArticlesFromDB_lds(article: Article) {
        return articleDAO.deleteArticles(article)
    }
}