package com.alanzlykh.hw2_photo_gallary

import com.alanzlykh.hw2_photo_gallary.api.FlickrApi
import retrofit2.HttpException
import java.io.IOException

class PhotoRepository (private val flickrApi: FlickrApi) {
    suspend fun fetchPhotos(page: Int, amount: Int): List<GalleryItem> {
        try {
            return flickrApi.fetchPhotos(page = page, amount = amount)
        } catch (e: IOException) {
            throw Exception(Consts.ioeExceptionFlag)
        } catch (e: HttpException) {
            throw Exception(Consts.httpExceptionFlag)
        }
    }
}