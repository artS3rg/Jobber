package com.artinc.jobber.di

import com.artinc.jobber.viewModels.ItemViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { ItemViewModel(get(), get()) }
}