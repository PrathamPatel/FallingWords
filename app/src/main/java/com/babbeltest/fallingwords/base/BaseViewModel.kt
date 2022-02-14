package com.babbeltest.fallingwords.base

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.babbeltest.fallingwords.App

/**
 * Created by : Pratham
 * BaseViewModel that can be used to extend all other viewModels
 */
abstract class BaseViewModel(app: App) : AndroidViewModel(app) {
    val baseNavigation = MutableLiveData<BaseNavigation>()
    open fun load(){}
    open fun onBackPressed(){}
}