package com.ostapr.core.network

import com.ostapr.core.domain.ArtistsRepository
import com.ostapr.core.network.ArtistsService.Companion.LAST_FM_BASE_URL
import com.ostapr.core.network.MusicBrainzService.Companion.MUSIC_BRAINZ_BASE_URL
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.annotation.AnnotationRetention.BINARY

@Qualifier
@Retention(BINARY)
annotation class LastFmRetrofit
@Qualifier
@Retention(BINARY)
annotation class MusicBrainzRetrofit

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ArtistsRepositoryModule {

    @Binds
    abstract fun bindArtistRepository(impl: ArtistsRepositoryImpl): ArtistsRepository

    companion object {

        @Provides
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient().newBuilder()
                .addInterceptor(LastFmApiKeyInterceptor())
                .build()
        }

        @Provides
        @Singleton
        @LastFmRetrofit
        fun provideLastFmRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(LAST_FM_BASE_URL)
            .build()

        @Provides
        fun provideArtistsService(@LastFmRetrofit retrofit: Retrofit): ArtistsService =
            retrofit.create()

        @Provides
        @Singleton
        @MusicBrainzRetrofit
        fun provideBrainzRetrofit(): Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(MUSIC_BRAINZ_BASE_URL)
            .build()

        @Provides
        fun provideMusicBrainzService(@MusicBrainzRetrofit retrofit: Retrofit): MusicBrainzService =
            retrofit.create()

    }
}

internal class LastFmApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedUrl = originalRequest.url().newBuilder()
            .addQueryParameter("api_key", BuildConfig.LAST_FM_API_KEY).build()
        val modifiedRequest = originalRequest.newBuilder().url(modifiedUrl).build()
        return chain.proceed(modifiedRequest)
    }
}