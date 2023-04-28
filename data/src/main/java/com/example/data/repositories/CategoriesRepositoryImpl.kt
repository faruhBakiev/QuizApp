package com.example.data.repositories

import com.example.data.dtos.toDomain
import com.example.data.remote.apiservices.CategoriesApiService
import com.example.domain.either.Either
import com.example.domain.models.ResultsItem
import com.example.domain.repositories.CategoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepositoryImpl @Inject constructor(private val service: CategoriesApiService) :
    CategoriesRepository {

    override fun fetchCategories(categories: Int, difficulty: String, amount: Int) = flow<Either<String, List<ResultsItem>>> {
        emit(Either.Right(service.fetchCategories(1 , categories,  difficulty).data.map {
            it.toDomain()
        }))
    }.flowOn(Dispatchers.IO).catch {
        emit(Either.Left(it.localizedMessage ?: "ERROR"))
    }
}