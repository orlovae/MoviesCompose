package ru.alexandrorlov.moviescompose.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.alexandrorlov.moviescompose.config.DataLocalConfig
import ru.alexandrorlov.moviescompose.model.entity.MovieDb

@Dao
interface MovieDao {
    @Query("SELECT * FROM ${DataLocalConfig.Movie.TABLE_NAME}")
    suspend fun getAll(): List<MovieDb>

    @Query("SELECT * FROM ${DataLocalConfig.Movie.TABLE_NAME} WHERE " +
            "${DataLocalConfig.Movie.COLUMN_ID} = :id")
    suspend fun getById(id: Int): MovieDb

    @Update
    suspend fun update(movieDb: MovieDb)

    @Insert
    suspend fun insertAll(movieDbList: List<MovieDb>)

    @Query("DELETE FROM ${DataLocalConfig.Movie.TABLE_NAME}")
    suspend fun deleteAll()
}