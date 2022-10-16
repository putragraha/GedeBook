package com.ptn.gedebook.fixture

import com.ptn.gedebook.book.domain.entity.Book

fun newBook(
    withId: Int = -1,
    withTitle: String = "",
    withAuthor: String = "",
    withImageUrl: String = "",
    withDownloadCount: Int = 0,
    withBookshelf: String = ""
) = Book(
    id = withId,
    title = withTitle,
    author = withAuthor,
    imageUrl = withImageUrl,
    downloadCount = withDownloadCount,
    bookshelf = withBookshelf
)
