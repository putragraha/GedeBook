package com.ptn.gedebook.core.di

import com.ptn.gedebook.book.data.service.BookService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun providesBookService(retrofit: Retrofit): BookService {
        return retrofit.create(BookService::class.java)
    }
}
