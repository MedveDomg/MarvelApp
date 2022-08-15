package com.medvedomg.marvelapp.presentation

import com.medvedomg.marvelapp.presentation.details.DetailsViewModel
import com.medvedomg.marvelapp.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        MainViewModel()
    }

    viewModel {
        DetailsViewModel(getDetailsUseCase = get())
    }
}