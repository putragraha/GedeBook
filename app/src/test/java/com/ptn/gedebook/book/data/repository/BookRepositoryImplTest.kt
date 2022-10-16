package com.ptn.gedebook.book.data.repository

import com.ptn.gedebook.book.data.datasource.BookRemoteDataSource
import com.ptn.gedebook.book.domain.entity.Book
import com.ptn.gedebook.fixture.newBookModel
import com.ptn.gedebook.fixture.newBookResultModel
import com.ptn.gedebook.fixture.newFormatModel
import com.ptn.gedebook.fixture.newPersonModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
class BookRepositoryImplTest {

    @MockK
    lateinit var bookRemoteDataSource: BookRemoteDataSource

    @InjectMockKs
    lateinit var bookRepository: BookRepositoryImpl

    private val book by lazy {
        Book(
            id = 1,
            title = "Book Title",
            author = "Author 1, Author 2",
            imageUrl = "image_url",
            downloadCount = 1,
            bookshelf = "Bookshelf 1, Bookshelf 2"
        )
    }

    private val bookModel by lazy {
        newBookModel(
            withBookResultModels = listOf(
                newBookResultModel(
                    withId = 1,
                    withTitle = "Book Title",
                    withAuthors = listOf(
                        newPersonModel(withName = "Author 1"),
                        newPersonModel(withName = "Author 2")
                    ),
                    withFormats = newFormatModel(withImageJpeg = "image_url"),
                    withDownloadCount = 1,
                    withBookshelves = listOf("Bookshelf 1", "Bookshelf 2")
                )
            )
        )
    }

    private val books by lazy {
        listOf(book)
    }

    @Test
    fun `when getBooks then invoke bookRemoteDataSource getBooks`() {
        // given
        coEvery { bookRemoteDataSource.getBooks() }.answers { Result.success(newBookModel()) }

        // when
        runTest {
            bookRepository.getBooks()
        }

        // then
        coVerify { bookRemoteDataSource.getBooks() }
    }

    @Test
    fun `given bookRemoteDataSource getBooks return Result success with BookResultModel when getBooks then return Result success with list of Book`() {
        // given
        coEvery { bookRemoteDataSource.getBooks() }.answers { Result.success(bookModel) }

        runTest {
            // when
            val actual = bookRepository.getBooks().getOrNull()

            // then
            assertEquals(books, actual)
        }
    }

    @Test
    fun `given bookRemoteDataSource getBooks return Result failure when getBooks then return Result failure`() {
        // given
        val exception = Exception()
        coEvery { bookRemoteDataSource.getBooks() }.answers { Result.failure(exception) }

        runTest {
            // when
            val actual = bookRepository.getBooks().exceptionOrNull()

            // then
            assertEquals(exception, actual)
        }
    }
}
