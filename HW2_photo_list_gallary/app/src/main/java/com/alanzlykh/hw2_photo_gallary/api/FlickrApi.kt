package com.alanzlykh.hw2_photo_gallary.api

import com.alanzlykh.hw2_photo_gallary.Consts
import com.alanzlykh.hw2_photo_gallary.GalleryItem
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface FlickrApi {
    @GET("search")
    suspend fun fetchPhotos(@Header("x-api-key") key: String = Consts.apiKey,
                            @Query("page") page: Int,
                            @Query("limit") amount: Int): List<GalleryItem>
}