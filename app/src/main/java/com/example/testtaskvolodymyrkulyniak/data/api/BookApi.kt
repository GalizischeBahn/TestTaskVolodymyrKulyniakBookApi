package com.example.testtaskvolodymyrkulyniak.data.api

import com.example.testtaskvolodymyrkulyniak.data.models.BookApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "api.giphy.com"
private const val API_TOKEN = "JeWhJhXDYGz8BGxh5HxF6U0GrpcYAqS9"
interface BookApi {

@GET("svc/books/v3/lists.json")
suspend fun retrieve(
    @Query("api-key")
    token: String = API_TOKEN,
    @Query("list")
    query: String
): Response<BookApiResponse>
}