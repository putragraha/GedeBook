package com.ptn.gedebook.book.domain.entity

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val imageUrl: String,
    val downloadCount: Int,
    val bookshelf: String
)
