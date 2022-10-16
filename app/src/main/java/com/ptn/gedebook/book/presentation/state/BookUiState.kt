package com.ptn.gedebook.book.presentation.state

import com.ptn.gedebook.book.domain.entity.Book

sealed interface BookUiState {

    data class Success(val books: List<Book>): BookUiState

    object Empty: BookUiState

    object Failed: BookUiState

    object Loading: BookUiState

    object LoadComplete: BookUiState
}
