package com.example.testtaskvolodymyrkulyniak.data.repository

import androidx.paging.PagingSource
import com.example.android.codelabs.paging.data.BooksRemoteMediator
import com.example.android.codelabs.paging.db.BooksDatabase
import com.example.testtaskvolodymyrkulyniak.data.api.BookApi
import com.example.testtaskvolodymyrkulyniak.data.db.BookDao
import com.example.testtaskvolodymyrkulyniak.data.models.Result
import javax.inject.Inject

class PagingSourceRepositoryImpl @Inject constructor(val apiService: BookApi, val database: BooksDatabase): PagingSourceRepositoryInterface {

    override fun remoteMediatorFactory(query: String): BooksRemoteMediator {
        return BooksRemoteMediator(booksDatabase = database, service = apiService, query = query )
    }
    override fun pagingSourceFactory(query: String) : PagingSource<Int, Result> {
        return database.booksDao().booksByNameList(query)
    }

}