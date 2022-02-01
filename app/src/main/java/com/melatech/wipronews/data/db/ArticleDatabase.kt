package com.melatech.wipronews.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.melatech.wipronews.data.model.Article

/**
 *created by Jason Junior Calvert on 31/01/2022.
 */
@Database(entities = [Article::class], version = 1, exportSchema = false)

@TypeConverters(Converters::class)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun getArticleDAO_db(): ArticleDAO
}