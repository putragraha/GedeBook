package com.ptn.gedebook.book.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptn.gedebook.book.domain.entity.Book
import com.ptn.gedebook.book.domain.usecase.GetBooksUseCase
import com.ptn.gedebook.book.presentation.state.BookUiState
import com.ptn.gedebook.book.presentation.state.BookUiState.Empty
import com.ptn.gedebook.book.presentation.state.BookUiState.Failed
import com.ptn.gedebook.book.presentation.state.BookUiState.LoadComplete
import com.ptn.gedebook.book.presentation.state.BookUiState.Loading
import com.ptn.gedebook.book.presentation.state.BookUiState.Success
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookViewModel(private val getBooksUseCase: GetBooksUseCase): ViewModel() {

    private val _bookFlow: MutableStateFlow<BookUiState> = MutableStateFlow(Loading)
    val bookFlow: StateFlow<BookUiState>
        get() = _bookFlow

    fun getBooks() {
        viewModelScope.launch {
            getBooksUseCase.execute().fold(
                onSuccess = {
                    _bookFlow.value = LoadComplete
                    emitResultSuccess(it)
                },
                onFailure = {
                    _bookFlow.value = LoadComplete
                    _bookFlow.value = Failed
                }
            )
        }
    }

    private fun emitResultSuccess(books: List<Book>) {
        when {
            books.isEmpty() -> _bookFlow.value = Empty
            else -> _bookFlow.value = Success(books)
        }
    }
}