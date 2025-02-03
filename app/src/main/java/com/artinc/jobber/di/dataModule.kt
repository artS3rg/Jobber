package com.artinc.jobber.di

import com.artinc.jobber.apis.ApiService
import com.artinc.jobber.interfaces.ApiRepository
import com.artinc.jobber.repositories.ApiRepositoryImpl
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