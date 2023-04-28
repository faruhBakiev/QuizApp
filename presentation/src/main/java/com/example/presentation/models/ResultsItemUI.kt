package com.example.presentation.models

import com.example.domain.models.ResultsItem


data class ResultsItemUI(
    val difficulty: String = "",
    val question: String = "",
    val correctAnswer: String = "",
    val incorrectAnswers: List<String>?,
    val category: String = "",
    val type: String = ""
)

fun ResultsItem.toUI(): ResultsItemUI {
    return ResultsItemUI(
        difficulty,
        question,
        correctAnswer,
        incorrectAnswers,
        category,
        type
    )
}