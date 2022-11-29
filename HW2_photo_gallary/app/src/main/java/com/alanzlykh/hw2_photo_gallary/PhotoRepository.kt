package com.alanzlykh.hw2_photo_gallary

import com.alanzlykh.hw2_photo_gallary.api.FlickrApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class PhotoRepository {
    private val flickrApi: FlickrApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        flickrApi = retrofit.create<FlickrApi>()
    }
    suspend fun fetchPhotos() = flickrApi.fetchPhotos().photos.galleryItems
}