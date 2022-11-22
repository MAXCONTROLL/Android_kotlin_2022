package com.alanzlykh.hw2_photo_gallary.api

import retrofit2.http.GET

interface FlickrApi {
    @GET("/")
    suspend fun getContent(): String
}