package com.alanzlykh.hw2_photo_gallary.api

import com.alanzlykh.hw2_photo_gallary.Consts
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object FlickrApiObj {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Consts.baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    val api: FlickrApi = retrofit.create()
}