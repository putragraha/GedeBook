package com.ptn.gedebook.book.data.datasource

import com.ptn.gedebook.book.data.model.BookModel

interface BookRemoteDataSource {

    suspend fun getBooks(): Result<BookModel>
}
