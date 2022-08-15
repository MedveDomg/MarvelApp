package com.medvedomg.marvelapp.data

import com.squareup.moshi.Json

data class ComicsDetailsResponse(
    @field:Json(name = "data")
    val data: DataResponse
)

data class DataResponse(
    @field:Json(name = "results")
    val results: List<ResultResponse>
)

data class ResultResponse(
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "thumbnail")
    val thumbnail: ThumbnailResponse
)

data class ThumbnailResponse(
    @field:Json(name = "path")
    val path: String,
    @field:Json(name = "extension")
    val extension: String,
)