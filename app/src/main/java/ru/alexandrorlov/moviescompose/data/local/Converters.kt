package ru.alexandrorlov.moviescompose.data.local

import androidx.room.TypeConverter
import ru.alexandrorlov.moviescompose.config.DataRemoteConfig.SEPARATE_SYMBOL

class Converters {
    @TypeConverter
    fun mapStringToList(string: String): List<String> {
        return string.split(SEPARATE_SYMBOL)
    }

    @TypeConverter
    fun mapListToString(list: List<String>): String {
        return list.joinToString(SEPARATE_SYMBOL)
    }

    @TypeConverter
    fun mapIntToList(string: String): List<Int> {
        return if (string.isNotEmpty()) {
            val listString: List<String> = string.split(SEPARATE_SYMBOL).map { it.trim() }
            listString.map { item ->
                item.toInt()
            }
        } else emptyList()
    }

    @TypeConverter
    fun mapListToInt(list: List<Int>): String {
        return list.joinToString(SEPARATE_SYMBOL)
    }
}