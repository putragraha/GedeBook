package com.ptn.gedebook.book.di

import com.ptn.gedebook.book.data.datasource.BookRemoteDataSource
import com.ptn.gedebook.book.data.datasource.BookRemoteDataSourceImpl
import com.ptn.gedebook.book.data.repository.BookRepositoryImpl
import com.ptn.gedebook.book.data.service.BookService
import com.ptn.gedebook.book.domain.repository.BookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class BookModule {

    @Provides
    fun providesBookRepository(bookRemoteDataSource: BookRemoteDataSource): BookRepository {
        return BookRepositoryImpl(bookRemoteDataSource)
    }

    @Provides
    fun providesBookRemoteDataSource(bookService: BookService): BookRemoteDataSource {
        return BookRemoteDataSourceImpl(bookService)
    }
}
