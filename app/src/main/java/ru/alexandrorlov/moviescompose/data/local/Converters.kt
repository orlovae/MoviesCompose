package ru.alexandrorlov.moviescompose.data.local

import android.util.Log
import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun mapStringToList(string: String): List<String> {
        return string.split("|")
    }

    @TypeConverter
    fun mapListToString(list: List<String>): String {
        return list.joinToString("|")
    }

    @TypeConverter
    fun mapIntToList(string: String): List<Int> {
        return if (string.isNotEmpty()) {
            val listString: List<String> = string.split("|").map { it.trim() }
            listString.map { item ->
                item.toInt()
            }
        } else emptyList()
    }

    @TypeConverter
    fun mapListToInt(list: List<Int>): String {
        return list.joinToString("|")
    }
}