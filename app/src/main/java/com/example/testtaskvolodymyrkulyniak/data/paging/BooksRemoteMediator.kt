

package com.example.android.codelabs.paging.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.android.codelabs.paging.db.RemoteKeys
import com.example.android.codelabs.paging.db.BooksDatabase
import com.example.testtaskvolodymyrkulyniak.data.api.BookApi
import com.example.testtaskvolodymyrkulyniak.data.models.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException


private const val GITHUB_STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class BooksRemoteMediator(
    private val query: String?,
    private val service: BookApi,
    private val booksDatabase: BooksDatabase
) : RemoteMediator<Int, Result>() {

    override suspend fun initialize(): InitializeAction {

        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Result>): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: GITHUB_STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)

                val prevKey = remoteKeys?.prevKey
                if (prevKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                if (nextKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                nextKey
            }
        }

        val apiQuery = query

        try {
            val apiResponse = service.retrieve(query = apiQuery!!)

            val results  = apiResponse.body()?.results ?: mutableListOf<Result>()
            val formatted = mutableListOf<Result>()
            for (book in results) {
                val formattedName = book.list_name.replace(" ", "").replace("-", "").lowercase()
                book.list_name = formattedName
                formatted.add(book)
            }
            val endOfPaginationReached = results.isEmpty()
            booksDatabase.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    booksDatabase.remoteKeysDao().clearRemoteKeys()

                }
                val prevKey = if (page == GITHUB_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = formatted.map {
                    RemoteKeys(bookID = it.id, prevKey = prevKey, nextKey = nextKey)

                }
                Log.d("keys", keys.toString())
                booksDatabase.remoteKeysDao().insertAll(keys)
                booksDatabase.booksDao().clearBooks(apiQuery.replace("-", ""))
                booksDatabase.booksDao().insert(formatted)

            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Result>): RemoteKeys? {

        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { bookId ->

                withContext(Dispatchers.IO){
                booksDatabase.remoteKeysDao().remoteKeysBookId(bookId.id)}
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Result>): RemoteKeys? {

        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { it ->
                booksDatabase.remoteKeysDao().remoteKeysBookId(it.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Result>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { it ->
                booksDatabase.remoteKeysDao().remoteKeysBookId(it)
            }
        }
    }
}
