package ru.alexandrorlov.moviescompose.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import ru.alexandrorlov.moviescompose.config.DataRemoteConfig
import ru.alexandrorlov.moviescompose.config.DataRemoteConfig.JSON
import ru.alexandrorlov.moviescompose.data.remote.TMDBApiService

@Module
class RemoteModule {
    @Provides
    fun provideRetrofitService(
        factory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): TMDBApiService {
        return Retrofit.Builder()
            .baseUrl(DataRemoteConfig.getBaseURLGet())
            .addConverterFactory(factory)
            .client(okHttpClient)
            .build()
            .create(TMDBApiService::class.java)
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun provideFactory(): Converter.Factory {
        val json = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }
        val contentType = JSON.toMediaType()
        return json.asConverterFactory(contentType)
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addNetworkInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}