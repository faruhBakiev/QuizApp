package com.example.domain.usecases

import com.example.domain.either.Either
import com.example.domain.models.ResultsItem
import com.example.domain.repositories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCategoriesUseCase @Inject constructor(private val repository: CategoriesRepository) {

    operator fun invoke(
        category: Int, difficulty: String,amount: Int
    ): Flow<Either<String, List<ResultsItem>>> = repository.fetchCategories(category, difficulty, amount)

}