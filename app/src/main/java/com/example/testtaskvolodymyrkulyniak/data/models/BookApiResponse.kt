package com.example.testtaskvolodymyrkulyniak.data.models

data class BookApiResponse(
    val copyright: String,
    val last_modified: String,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)