package com.example.testtaskvolodymyrkulyniak.data.models

import com.example.testtaskvolodymyrkulyniak.R

data class BookOptions(val option: Int,
                       val query: String) {


    companion object {
         val listOfOptions = listOf<BookOptions>(BookOptions(R.string.combined_fiction, "combined-print-and-e-book-fiction"),
             BookOptions(R.string.hardcover_fiction, "hardcover-fiction"),
             BookOptions(R.string.paperback_trade_fiction, "trade-fiction-paperback"),
             BookOptions(R.string.combined_nonfiction, "combined-print-and-e-book-nonfiction"),
             BookOptions(R.string.hardcover_nonfiction, "hardcover-nonfiction"),
             BookOptions(R.string.paperback_trade_nonfiction, "paperback-nonfiction")
             )
    }



}
