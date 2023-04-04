package ru.alexandrorlov.moviescompose.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.alexandrorlov.moviescompose.config.DataLocalConfig
import ru.alexandrorlov.moviescompose.model.entity.GenreDb

@Dao
interface GenreDao {
    @Query("SELECT * FROM ${DataLocalConfig.Genre.TABLE_NAME}")
    suspend fun getAll(): List<GenreDb>

    @Query(
        "SELECT * FROM ${DataLocalConfig.Genre.TABLE_NAME} WHERE " +
                "${DataLocalConfig.Genre.COLUMN_ID} = :id"
    )
    suspend fun getById(id: Int): GenreDb

    @Update
    suspend fun update(genreDb: GenreDb)

    @Insert
    suspend fun insertAll(genreDbList: List<GenreDb>)

    @Query("DELETE FROM ${DataLocalConfig.Genre.TABLE_NAME}")
    suspend fun deleteAll()
}