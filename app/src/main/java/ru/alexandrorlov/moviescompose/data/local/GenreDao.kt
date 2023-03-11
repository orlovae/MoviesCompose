package ru.alexandrorlov.moviescompose.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.alexandrorlov.moviescompose.config.DataLocalConfig
import ru.alexandrorlov.moviescompose.model.entity.GenreDb

@Dao
interface GenreDao {
    @Query("SELECT * FROM ${DataLocalConfig.Genre.TABLE_NAME}")
    fun getAll(): Flow<List<GenreDb>>

    @Query("SELECT * FROM ${DataLocalConfig.Genre.TABLE_NAME} WHERE " +
            "${DataLocalConfig.Genre.COLUMN_ID} = :id")
    fun getById(id: Int): Flow<GenreDb>

    @Update
    suspend fun update(genreDb: GenreDb)

    @Insert
    suspend fun insertAll(genreDbList: List<GenreDb>)

    @Query("DELETE FROM ${DataLocalConfig.Genre.TABLE_NAME}")
    suspend fun deleteAll()
}