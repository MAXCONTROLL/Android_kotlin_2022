package com.alanzlykh.hw2_photo_gallary

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GalleryItem (
    val id: String,
    @Json(name = "url") val url: String
)