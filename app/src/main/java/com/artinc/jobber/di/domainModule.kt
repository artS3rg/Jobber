package com.artinc.jobber.di

import com.artinc.jobber.usecases.GetOffersUseCase
import com.artinc.jobber.usecases.GetVacanciesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetOffersUseCase(get()) }
    factory { GetVacanciesUseCase(get()) }
}