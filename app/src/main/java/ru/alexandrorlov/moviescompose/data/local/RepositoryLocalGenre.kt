package ru.alexandrorlov.moviescompose.data.local

import ru.alexandrorlov.moviescompose.model.ui.Genre
import javax.inject.Inject

class RepositoryLocalGenre @Inject constructor(
    private val db: LocalDataBase,
    private val mapperDbToUi: MapperDbToUi,
    private val mapperUiToDb: MapperUiToDb
) {

    suspend fun getAllGenre(): List<Genre> {
        return mapperDbToUi.mapToGenreUiList(
            db.GenreDao().getAll()
        )
    }

    suspend fun getGenre(id: Int): Genre {
        return mapperDbToUi.mapToGenreUi(
            db.GenreDao().getById(id)
        )
    }

    suspend fun insertAll(genreList: List<Genre>) {
        db.GenreDao().insertAll(
            mapperUiToDb.mapGenreListToGenreDbList(genreList)
        )
    }

    suspend fun updateGenre(genre: Genre) {
        db.GenreDao().update(
            mapperUiToDb.mapToGenreDb(genre)
        )
    }

    suspend fun deleteAll() {
        db.GenreDao().deleteAll()
    }
}