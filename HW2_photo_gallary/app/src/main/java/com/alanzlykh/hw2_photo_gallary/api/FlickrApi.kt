package com.alanzlykh.hw2_photo_gallary.api

import com.alanzlykh.hw2_photo_gallary.Consts
import com.alanzlykh.hw2_photo_gallary.FlickrResponse
import retrofit2.http.GET



interface FlickrApi {
    @GET(
        "/services/rest/?method=flickr.interestingness.getList" +
                "&api_key=${Consts.apiKey}" +
                "&format=json" +
                "&nojsoncallback=1" +
                "&extras=url_s"
    )
    suspend fun fetchPhotos(): FlickrResponse
}