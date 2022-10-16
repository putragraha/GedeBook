package com.ptn.gedebook.book.data.datasource

import com.ptn.gedebook.book.data.service.BookService
import com.ptn.gedebook.fixture.newBookModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import retrofit2.Response

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
class BookRemoteDataSourceImplTest {

    @MockK
    lateinit var bookService: BookService

    @InjectMockKs
    lateinit var bookRemoteDataSource: BookRemoteDataSourceImpl

    @Test
    fun `given bookService getBooks is success when getBooks then return result success`() {
        // given
        coEvery { bookService.getBooks() }.answers { Response.success(newBookModel()) }

        runTest {
            // when
            val actual = bookRemoteDataSource.getBooks()

            // then
            coVerify { bookService.getBooks() }
            assertTrue(actual.isSuccess)
        }
    }

    @Test
    fun `given bookService getBooks with null response body when getBooks then return result failure`() {
        // given
        coEvery { bookService.getBooks() }.answers { Response.success(null) }

        runTest {
            // when
            val actual = bookRemoteDataSource.getBooks()

            // then
            coVerify { bookService.getBooks() }
            assertTrue(actual.isFailure)
        }
    }

    @Test
    fun `given bookService getBooks is failed when getBooks then return result failure`() {
        // given
        coEvery { bookService.getBooks() }.answers {
            val errorBody = ResponseBody.create(MediaType.parse("error"), "error")
            Response.error(404, errorBody)
        }

        runTest {
            // when
            val actual = bookRemoteDataSource.getBooks()

            // then
            coVerify { bookService.getBooks() }
            assertTrue(actual.isFailure)
        }
    }

    @Test
    fun `given bookService getBooks thrown exception when getBooks then return result failure`() {
        // given
        coEvery { bookService.getBooks() } throws Exception()

        runTest {
            // when
            val actual = bookRemoteDataSource.getBooks()

            // then
            coVerify { bookService.getBooks() }
            assertTrue(actual.isFailure)
        }
    }
}
