package com.ostapr.core.network

import com.ostapr.core.domain.ArtistsRepository
import com.ostapr.core.network.ArtistsService.Companion.LAST_FM_BASE_URL
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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ArtistsRepositoryModule {

    @Binds
    abstract fun bindArtistRepository(impl: ArtistsRepositoryImpl): ArtistsRepository

    companion object {

        @Provides
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient().newBuilder()
                .addInterceptor(ApiKeyInterceptor())
                .build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(LAST_FM_BASE_URL)
            .build()

        @Provides
        fun provideArtistsService(retrofit: Retrofit): ArtistsService =
            retrofit.create(ArtistsService::class.java)
    }
}
class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedUrl = originalRequest.url().newBuilder()
            .addQueryParameter("api_key", BuildConfig.LAST_FM_API_KEY).build()
        val modifiedRequest = originalRequest.newBuilder().url(modifiedUrl).build()
        return chain.proceed(modifiedRequest)
    }
}