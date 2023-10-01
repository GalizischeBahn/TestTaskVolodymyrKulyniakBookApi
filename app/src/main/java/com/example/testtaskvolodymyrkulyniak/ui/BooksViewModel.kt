package com.example.testtaskvolodymyrkulyniak.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.android.codelabs.paging.db.BooksDatabase
import com.example.testtaskvolodymyrkulyniak.data.models.Result
import com.example.testtaskvolodymyrkulyniak.data.repository.PagingSourceRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@HiltViewModel
class BooksViewModel @Inject constructor(val repo: PagingSourceRepositoryInterface): ViewModel() {

@OptIn(ExperimentalPagingApi::class)
fun fetchData(query: String) : Flow<PagingData<Result>> {

        return Pager(
             config = PagingConfig(pageSize = 15, enablePlaceholders = true),
             remoteMediator = repo.remoteMediatorFactory(query)){
             repo.pagingSourceFactory(query.replace("-", ""))}.flow.flowOn(Dispatchers.IO)
   }

}