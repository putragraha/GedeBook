package com.ptn.gedebook.book.data.model

import com.ptn.gedebook.book.domain.entity.Book
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookResultModel(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "authors") val authors: List<PersonModel>,
    @Json(name = "subjects") val subjects: List<String>,
    @Json(name = "translators") val translators: List<PersonModel>,
    @Json(name = "bookshelves") val bookshelves: List<String>,
    @Json(name = "languages") val languages: List<String>,
    @Json(name = "media_type") val mediaType: String,
    @Json(name = "formats") val formats: FormatModel,
    @Json(name = "download_count") val downloadCount: Int,
    @Json(name = "copyright") val copyright: Boolean? = null
) {

    fun toBook() = Book(
        id = id,
        title = title,
        author = authors.joinToString { it.name },
        imageUrl = formats.imageJpeg ?: "",
        downloadCount = downloadCount,
        bookshelf = bookshelves.joinToString()
    )
}
