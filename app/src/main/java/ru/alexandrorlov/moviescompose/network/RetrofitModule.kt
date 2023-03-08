package ru.alexandrorlov.moviescompose.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.alexandrorlov.moviescompose.config.NetworkConfig

object RetrofitModule {
    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    private val contentType = "application/json".toMediaType()

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addNetworkInterceptor(loggingInterceptor)
        .build()

    @OptIn(ExperimentalSerializationApi::class)
    @Suppress("EXPERIMENTAL_API_USAGE")
    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(
            NetworkConfig.getBaseURLGet()
        )
        //TODO Посмотреть где получаем Cofiguration, сделать проверку на соответствие baseURL из Network.kt из сети. Если неверный то менять.
        .addConverterFactory(json.asConverterFactory(contentType))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(httpClient)
        .build()

    val tmdbApiService: TMDBApiService = retrofit().create(TMDBApiService::class.java)
}