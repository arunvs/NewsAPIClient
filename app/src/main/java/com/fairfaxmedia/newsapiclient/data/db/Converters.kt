package com.fairfaxmedia.newsapiclient.data.db

import androidx.room.TypeConverter
import com.fairfaxmedia.newsapiclient.data.model.RelatedImage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {
    @TypeConverter
    fun fromString(value: String?): List<RelatedImage?>? {
        val listType: Type = object : TypeToken<List<RelatedImage?>?>() {}.getType()
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<RelatedImage>): String? {
        val gson = Gson()
        return gson.toJson(list)
    }


}
