package com.ptn.gedebook.book.domain.usecase

import com.ptn.gedebook.book.domain.entity.Book
import com.ptn.gedebook.book.domain.repository.BookRepository
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(private val bookRepository: BookRepository) {

    suspend fun execute(): Result<List<Book>> {
        return bookRepository.getBooks()
    }
}
