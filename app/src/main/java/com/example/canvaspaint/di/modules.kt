package com.example.canvaspaint.di

import com.example.canvaspaint.CanvasViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {

    viewModel {
        CanvasViewModel()
    }

}