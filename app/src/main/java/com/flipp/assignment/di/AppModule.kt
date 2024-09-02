package com.flipp.assignment.di

import com.flipp.assignment.data.ISearchRepository
import com.flipp.assignment.data.SearchRepository
import com.flipp.assignment.network.NetworkAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideNetworkApi(): NetworkAPI {
    return Retrofit.Builder()
      .baseUrl(NetworkAPI.BASE_URL)
      .addConverterFactory(
        MoshiConverterFactory.create(
          Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        )
      )
      .build()
      .create()
  }

  @Provides
  @Singleton
  fun provideSearchRepository(networkAPI: NetworkAPI): ISearchRepository {
    return SearchRepository(networkAPI)
  }

}