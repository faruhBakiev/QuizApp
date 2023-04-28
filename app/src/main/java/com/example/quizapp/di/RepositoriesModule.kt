package com.example.quizapp.di

import com.example.data.repositories.CategoriesRepositoryImpl
import com.example.domain.repositories.CategoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun bindCategoryRepositories(repositoryImpl: CategoriesRepositoryImpl): CategoriesRepository
}