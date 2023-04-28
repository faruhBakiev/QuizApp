package com.example.data.remote.apiservices

import com.example.data.dtos.ResponseDto
import com.example.data.dtos.ResultsItemDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoriesApiService {

    @GET("api.php")
    suspend fun fetchCategories(
        @Query("amount") amount: Int?,
        @Query("category") category: Int?,
        @Query("difficulty") difficulty: String
    ): ResponseDto<ResultsItemDto>
}