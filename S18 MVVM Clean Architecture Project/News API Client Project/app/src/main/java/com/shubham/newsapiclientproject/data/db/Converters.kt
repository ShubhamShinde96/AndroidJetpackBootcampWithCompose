package com.shubham.newsapiclientproject.data.db

import androidx.room.TypeConverter
import com.shubham.newsapiclientproject.data.model.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }

}