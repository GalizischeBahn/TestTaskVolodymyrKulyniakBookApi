package com.example.testtaskvolodymyrkulyniak.di

import com.example.testtaskvolodymyrkulyniak.data.api.BookApi
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
    object NetworkModule {

        @Provides
        @Singleton
        fun provideMyApi(): BookApi {
            return Retrofit.Builder()
                .baseUrl("https://api.nytimes.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient
                    .Builder().build())
                .build()
                .create(BookApi::class.java)
        }
//        @Provides
//        @Singleton
//        fun provideNoteDatabase(@ApplicationContext context: Context): BooksDatabase {
//            return Room.databaseBuilder(
//                context,
//                BooksDatabase::class.java,
//                BooksDatabase.DATABASE_NAME
//            ).build()
//        }

    }