package com.artinc.data

import com.artinc.data.apis.ApiService
import com.artinc.domain.interfaces.ApiRepository
import com.artinc.data.repositories.ApiRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.dsl.module

val dataModule = module {
    single { createRetrofit() }
    single { get<Retrofit>().create(ApiService::class.java) }
    single<ApiRepository> { ApiRepositoryImpl(get()) }
}

fun createRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl("https://drive.usercontent.google.com/u/0/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()