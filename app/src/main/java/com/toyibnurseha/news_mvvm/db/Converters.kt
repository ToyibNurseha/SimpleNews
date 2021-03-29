package com.toyibnurseha.news_mvvm.db

import androidx.room.TypeConverter
import com.toyibnurseha.news_mvvm.models.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source) : String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String) : Source {
        return Source(name, name)
    }

}