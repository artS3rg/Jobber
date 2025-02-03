package com.artinc.data.apis

import com.artinc.domain.models.ApiResponse
import retrofit2.http.GET

interface ApiService {
    @GET("uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    suspend fun getApi(): ApiResponse
}