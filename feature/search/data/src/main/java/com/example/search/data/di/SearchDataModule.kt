package com.example.search.data.di

import com.example.search.data.Repository.SearchRepoImpl
import com.example.search.data.remote.SearchApiService
import com.example.search.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val Base_Url1="https://www.themealdb.com/"



@InstallIn(SingletonComponent::class)
@Module
object SearchDataModule {
     @Provides
     @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder().baseUrl(Base_Url1)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun getSearchApiService(retrofit: Retrofit):SearchApiService{
        return retrofit.create(SearchApiService::class.java)
    }

    @Provides
    fun provideSearchRepo(searchApiService: SearchApiService):SearchRepository{
        return SearchRepoImpl(searchApiService)
    }
}