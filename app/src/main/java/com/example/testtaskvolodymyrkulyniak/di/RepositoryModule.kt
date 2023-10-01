package com.example.testtaskvolodymyrkulyniak.di

import android.app.Application
import androidx.room.Room
import com.example.android.codelabs.paging.db.BooksDatabase
import com.example.testtaskvolodymyrkulyniak.data.api.BookApi
import com.example.testtaskvolodymyrkulyniak.data.repository.PagingSourceRepositoryImpl
import com.example.testtaskvolodymyrkulyniak.data.repository.PagingSourceRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPagingSourceRepository(pagingSourceRepositoryImpl: PagingSourceRepositoryImpl) :
            PagingSourceRepositoryInterface
}