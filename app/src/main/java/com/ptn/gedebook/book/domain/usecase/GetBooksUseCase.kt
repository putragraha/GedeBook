package com.ptn.gedebook.book.domain.usecase

import com.ptn.gedebook.book.domain.entity.Book
import com.ptn.gedebook.book.domain.repository.BookRepository

class GetBooksUseCase(private val bookRepository: BookRepository) {

    suspend fun execute(): Result<List<Book>> {
        return bookRepository.getBooks()
    }
}
