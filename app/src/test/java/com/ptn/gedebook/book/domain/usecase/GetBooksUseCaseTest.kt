package com.ptn.gedebook.book.domain.usecase

import com.ptn.gedebook.book.domain.repository.BookRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
internal class GetBooksUseCaseTest {

    @MockK
    lateinit var repository: BookRepository

    @InjectMockKs
    lateinit var getBooksUseCase: GetBooksUseCase

    @Test
    fun `when execute then repository getBooks should invoked`() {
        // given
        coEvery { repository.getBooks() }.answers { Result.success(emptyList()) }

        // when
        runTest {
            getBooksUseCase.execute()
        }

        // then
        coVerify { repository.getBooks() }
    }
}
