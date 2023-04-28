package com.example.domain.models


data class Response(
    val responseCode: Int = 0,
    val results: List<ResultsItem>,
)