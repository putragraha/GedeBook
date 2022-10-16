package com.ptn.gedebook.book.presentation.viewmodel

import app.cash.turbine.test
import com.ptn.gedebook.book.domain.usecase.GetBooksUseCase
import com.ptn.gedebook.book.presentation.state.BookUiState.Empty
import com.ptn.gedebook.book.presentation.state.BookUiState.Failed
import com.ptn.gedebook.book.presentation.state.BookUiState.LoadComplete
import com.ptn.gedebook.book.presentation.state.BookUiState.Loading
import com.ptn.gedebook.book.presentation.state.BookUiState.Success
import com.ptn.gedebook.fixture.newBook
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
class BookViewModelTest {

    @MockK
    lateinit var getBooksUseCase: GetBooksUseCase

    @InjectMockKs
    lateinit var bookViewModel: BookViewModel

    private val testDispatcher by lazy {
        StandardTestDispatcher()
    }

    @BeforeEach
    internal fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `given getBooksUseCase with List of Books when getBooks then emit BookUiState Success`() {
        // given
        val books = listOf(newBook())
        val expectedResult = Success(books)
        coEvery { getBooksUseCase.execute() }.answers { Result.success(books) }

        runTest {
            // when
            bookViewModel.getBooks()

            // then
            bookViewModel.bookFlow.test {
                assertEquals(Loading, awaitItem())
                assertEquals(LoadComplete, awaitItem())
                assertEquals(expectedResult, awaitItem())
            }
        }
    }

    @Test
    fun `given getBooksUseCase with List of Books is empty when getBooks then emit BookUiState Empty`() {
        // given
        coEvery { getBooksUseCase.execute() }.answers { Result.success(emptyList()) }

        runTest {
            // when
            bookViewModel.getBooks()

            // then
            bookViewModel.bookFlow.test {
                assertEquals(Loading, awaitItem())
                assertEquals(LoadComplete, awaitItem())
                assertEquals(Empty, awaitItem())
            }
        }
    }

    @Test
    fun `given getBooksUseCase with failure result when getBooks then emit BookUiState Failed`() {
        // given
        coEvery { getBooksUseCase.execute() }.answers { Result.failure(Exception()) }

        runTest {
            // when
            bookViewModel.getBooks()

            // then
            bookViewModel.bookFlow.test {
                assertEquals(Loading, awaitItem())
                assertEquals(LoadComplete, awaitItem())
                assertEquals(Failed, awaitItem())
            }
        }
    }

    @AfterEach
    internal fun tearDown() {
        Dispatchers.resetMain()
    }
}
