

package com.example.android.codelabs.paging.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testtaskvolodymyrkulyniak.data.db.BookDao
import com.example.testtaskvolodymyrkulyniak.data.db.Converters
import com.example.testtaskvolodymyrkulyniak.data.models.Result

@Database(
    entities = [Result::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class BooksDatabase : RoomDatabase() {

    abstract fun booksDao(): BookDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {

        const val DATABASE_NAME = "Books.db"

}}
