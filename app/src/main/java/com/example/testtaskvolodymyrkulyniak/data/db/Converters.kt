package com.example.testtaskvolodymyrkulyniak.data.db

import androidx.room.TypeConverter
import com.example.testtaskvolodymyrkulyniak.data.models.BookDetail
import com.example.testtaskvolodymyrkulyniak.data.models.Isbn
import com.example.testtaskvolodymyrkulyniak.data.models.Review
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun fromBookDetailList(bookDetails: List<BookDetail>): String {
        val gson = Gson()
        return gson.toJson(bookDetails)
    }

    @TypeConverter
    fun toBookDetailList(bookDetailsString: String): List<BookDetail> {
        val gson = Gson()
        val listType = object : TypeToken<List<BookDetail>>() {}.type
        return gson.fromJson(bookDetailsString, listType)
    }

    @TypeConverter
    fun fromIsbnList(isbnList: List<Isbn>): String {
        val gson = Gson()
        return gson.toJson(isbnList)
    }

    @TypeConverter
    fun toIsbnList(isbnListString: String): List<Isbn> {
        val gson = Gson()
        val listType = object : TypeToken<List<Isbn>>() {}.type
        return gson.fromJson(isbnListString, listType)
    }

    @TypeConverter
    fun fromReviewList(reviewList: List<Review>): String {
        val gson = Gson()
        return gson.toJson(reviewList)
    }

    @TypeConverter
    fun toReviewList(reviewListString: String): List<Review> {
        val gson = Gson()
        val listType = object : TypeToken<List<Review>>() {}.type
        return gson.fromJson(reviewListString, listType)
    }

}