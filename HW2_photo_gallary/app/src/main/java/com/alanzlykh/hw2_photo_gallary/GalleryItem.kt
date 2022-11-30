package com.alanzlykh.hw2_photo_gallary

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.UUID

@JsonClass(generateAdapter = true)
data class GalleryItem (
    val title: String,
    val id: String,
    @Json(name = "url_o") val url: String
)