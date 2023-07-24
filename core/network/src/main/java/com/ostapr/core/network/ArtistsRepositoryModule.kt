package com.ostapr.core.network

import com.google.gson.GsonBuilder
import com.ostapr.core.domain.ArtistsRepository
import com.ostapr.core.network.ArtistsService.Companion.LAST_FM_BASE_URL
import com.ostapr.core.network.MusicBrainzService.Companion.MUSIC_BRAINZ_BASE_URL
import com.ostapr.core.network.data.ArtistRelations
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
annotation class LastFmRelated

@Qualifier
@Retention(BINARY)
annotation class MusicBrainzRelated

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ArtistsRepositoryModule {

    @Binds
    abstract fun bindArtistRepository(impl: ArtistsRepositoryImpl): ArtistsRepository

    companion object {

        @Provides
        @LastFmRelated
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient().newBuilder()
                .addInterceptor(LastFmApiKeyInterceptor())
                .build()
        }

        @Provides
        @Singleton
        @LastFmRelated
        fun provideLastFmRetrofit(@LastFmRelated okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(LAST_FM_BASE_URL)
                .build()

        @Provides
        fun provideArtistsService(@LastFmRelated retrofit: Retrofit): ArtistsService =
            retrofit.create()

        @Provides
        @MusicBrainzRelated
        fun provideMusicBrainzOkHttpClient(): OkHttpClient {
            return OkHttpClient().newBuilder()
                .addInterceptor(UserAgentHeaderInterceptor())
                .build()
        }

        @Provides
        @Singleton
        @MusicBrainzRelated
        fun provideBrainzRetrofit(@MusicBrainzRelated okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().registerTypeAdapter(
                            ArtistRelations::class.java,
                            ArtistRelationsDeserializer()
                        ).create()
                    )
                )
                .client(okHttpClient)
                .baseUrl(MUSIC_BRAINZ_BASE_URL)
                .build()

        @Provides
        fun provideMusicBrainzService(@MusicBrainzRelated retrofit: Retrofit): MusicBrainzService =
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

internal class UserAgentHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .addHeader("User-Agent", "LastFM client/v0.0.1 (revakooa@gmail.com)")
            .build()
        return chain.proceed(modifiedRequest)
    }
}