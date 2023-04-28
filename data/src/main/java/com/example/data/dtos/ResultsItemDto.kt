package com.example.data.dtos

import com.example.domain.models.ResultsItem
import com.google.gson.annotations.SerializedName

data class ResultsItemDto(
    @SerializedName("difficulty")
    val difficulty: String = "",
    @SerializedName("question")
    val question: String = "",
    @SerializedName("correct_answer")
    val correctAnswer: String = "",
    @SerializedName("incorrect_answers")
    val incorrectAnswers: List<String>?,
    @SerializedName("category")
    val category: String = "",
    @SerializedName("type")
    val type: String = ""
)

fun ResultsItemDto.toDomain(): ResultsItem {
    return ResultsItem(
        difficulty,
        question,
        correctAnswer,
        incorrectAnswers,
        category,
        type
    )
}