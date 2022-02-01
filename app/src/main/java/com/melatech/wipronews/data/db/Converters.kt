package com.melatech.wipronews.data.db

import androidx.room.TypeConverter
import com.melatech.wipronews.data.model.Source

/**
 *created by Jason Junior Calvert on 31/01/2022.
 */
class Converters {

    @TypeConverter
    fun fromSource(source: Source): String{
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source{
        return Source(name, name)
    }
}