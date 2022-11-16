package com.ptn.gedebook.book.data.datasource

import com.ptn.gedebook.book.data.model.BookModel
import com.ptn.gedebook.book.data.service.BookService
import javax.inject.Inject

class BookRemoteDataSourceImpl @Inject constructor(
    private val bookService: BookService
) : BookRemoteDataSource {

    override suspend fun getBooks(): Result<BookModel> {
        return try {
            val response = bookService.getBooks()
            val bookModel = response.body()

            when {
                bookModel == null -> Result.failure(NullPointerException("Response body is empty"))
                response.isSuccessful -> Result.success(bookModel)
                else -> Result.failure(Exception("Response not OK"))
            }
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
