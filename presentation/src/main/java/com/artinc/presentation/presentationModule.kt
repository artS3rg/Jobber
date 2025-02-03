package com.artinc.presentation

import com.artinc.presentation.viewModels.ItemViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { ItemViewModel(get(), get()) }
}