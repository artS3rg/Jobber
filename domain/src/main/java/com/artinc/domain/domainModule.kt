package com.artinc.domain

import com.artinc.domain.usecases.GetOffersUseCase
import com.artinc.domain.usecases.GetVacanciesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetOffersUseCase(get()) }
    factory { GetVacanciesUseCase(get()) }
}