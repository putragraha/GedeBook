package com.ptn.gedebook.book.data.service

import com.ptn.gedebook.book.data.model.BookModel
import retrofit2.Response
import retrofit2.http.GET

interface BookService {

    @GET("books")
    suspend fun getBooks(): Response<BookModel>
}
