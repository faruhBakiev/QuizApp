package com.example.data.dtos

import com.google.gson.annotations.SerializedName

data class ResponseDto<T>(
    @SerializedName("response_code")
    val responseCode: Int,
    @SerializedName("results")
    val results: List<T>,
)

