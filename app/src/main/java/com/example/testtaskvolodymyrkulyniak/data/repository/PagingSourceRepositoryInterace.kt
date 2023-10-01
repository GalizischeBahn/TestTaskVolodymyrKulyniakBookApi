package com.example.testtaskvolodymyrkulyniak.data.repository

import androidx.paging.PagingSource
import com.example.android.codelabs.paging.data.BooksRemoteMediator
import com.example.testtaskvolodymyrkulyniak.data.models.Result

interface PagingSourceRepositoryInterface {

    fun remoteMediatorFactory(query: String) : BooksRemoteMediator
    fun pagingSourceFactory(query: String) : PagingSource<Int, Result>
}