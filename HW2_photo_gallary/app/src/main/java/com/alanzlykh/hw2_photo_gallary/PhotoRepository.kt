package com.alanzlykh.hw2_photo_gallary

import com.alanzlykh.hw2_photo_gallary.api.FlickrApi

class PhotoRepository (private val flickrApi: FlickrApi) {
    suspend fun fetchPhotos() = flickrApi.fetchPhotos().photos.galleryItems
}