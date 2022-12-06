package com.alanzlykh.hw2_photo_gallary

import com.squareup.moshi.JsonClass

@JsonClass (generateAdapter = true)
data class FlickrResponse (
    val photos: PhotoResponse
)