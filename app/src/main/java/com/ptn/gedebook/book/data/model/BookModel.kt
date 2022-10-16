package com.ptn.gedebook.book.data.model

import com.ptn.gedebook.book.domain.entity.Book
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookModel(
    @Json(name = "count") val count: Int,
    @Json(name = "next") val next: String? = null,
    @Json(name = "previous") val previous: String? = null,
    @Json(name = "results") val bookResultModels: List<BookResultModel>
) {

    fun toBooks(): List<Book> {
        return bookResultModels
            .map { it.toBook() }
    }
}
