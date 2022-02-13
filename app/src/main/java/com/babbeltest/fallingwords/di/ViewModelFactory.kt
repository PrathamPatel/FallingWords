package com.babbeltest.fallingwords.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by : Pratham
 */
class ViewModelFactory<T : ViewModel> @Inject constructor(private val viewModelProvider: Provider<T>) : ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelProvider.get() as T
    }
}