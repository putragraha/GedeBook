package com.ptn.gedebook.book.domain.repository

import com.ptn.gedebook.book.domain.entity.Book

interface BookRepository {

    suspend fun getBooks(): Result<List<Book>>
}
