package com.ptn.gedebook.book.data.repository

import com.ptn.gedebook.book.data.datasource.BookRemoteDataSource
import com.ptn.gedebook.book.domain.entity.Book
import com.ptn.gedebook.book.domain.repository.BookRepository
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookRemoteDataSource: BookRemoteDataSource
) : BookRepository {

    override suspend fun getBooks(): Result<List<Book>> {
        return bookRemoteDataSource.getBooks()
            .getOrElse { return Result.failure(it) }
            .let { Result.success(it.toBooks()) }
    }
}
