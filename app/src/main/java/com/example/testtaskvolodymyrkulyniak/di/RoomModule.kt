package com.example.testtaskvolodymyrkulyniak.di

import android.content.Context
import androidx.room.Room
import com.example.android.codelabs.paging.db.BooksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): BooksDatabase {
        return Room.databaseBuilder(
            context,
            BooksDatabase::class.java,
            BooksDatabase.DATABASE_NAME
        ).build()
    }
}
