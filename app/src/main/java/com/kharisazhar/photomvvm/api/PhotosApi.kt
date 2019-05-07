package com.kharisazhar.photomvvm.api

import com.kharisazhar.photomvvm.model.Photo
import io.reactivex.Single
import retrofit2.http.GET

interface PhotosApi {
    @GET("photos")
    fun getPhotos(): Single<List<Photo>>
}