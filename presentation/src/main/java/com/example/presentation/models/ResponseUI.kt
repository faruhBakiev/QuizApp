package com.example.presentation.models

import com.example.domain.models.Response
import com.example.domain.models.ResultsItem


data class ResponseUI(
    val responseCode: Int = 0,
    val results: List<ResultsItem>
)

fun Response.toUI(): ResponseUI {
    return ResponseUI(
        responseCode,
        results
    )
}