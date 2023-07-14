package com.ostapr.core.network

import com.ostapr.core.domain.ArtistsRepository
import com.ostapr.core.network.ArtistsService.Companion.LAST_FM_BASE_URL
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ArtistsRepositoryModule {

    @Binds
    abstract fun bindArtistRepository(impl: ArtistsRepositoryImpl): ArtistsRepository

    companion object {
        @Provides
        fun provideRetrofit(): Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(LAST_FM_BASE_URL)
            .build()

        @Provides
        fun provideArtistsService(retrofit: Retrofit): ArtistsService =
            retrofit.create(ArtistsService::class.java)
    }
}