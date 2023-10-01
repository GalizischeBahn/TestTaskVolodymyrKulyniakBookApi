package com.example.testtaskvolodymyrkulyniak.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testtaskvolodymyrkulyniak.data.models.Result

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(book: List<Result>)

    @Query("SELECT * FROM books")
     fun allBooks(): PagingSource<Int, Result>

    @Query("SELECT * FROM books WHERE list_name == :queryString")
    fun booksByNameList(queryString: String): PagingSource<Int, Result>

    @Query("DELETE FROM books WHERE list_name == :queryString")
     fun clearBooks(queryString: String)
}