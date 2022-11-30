package com.alanzlykh.hw2_photo_gallary.api

import com.alanzlykh.hw2_photo_gallary.FlickrResponse
import retrofit2.http.GET

private const val API_KEY = "c3f071db73578a2da404c7aaad225af8"

interface FlickrApi {
    @GET(
        "/services/rest/?method=flickr.interestingness.getList" +
                "&api_key=$API_KEY" +
                "&format=json" +
                "&nojsoncallback=1" +
                "&extras=url_s"
    )
    suspend fun fetchPhotos(): FlickrResponse
}