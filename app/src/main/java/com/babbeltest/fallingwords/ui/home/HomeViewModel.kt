package com.babbeltest.fallingwords.ui.home

import com.babbeltest.fallingwords.App
import com.babbeltest.fallingwords.base.BaseNavigation
import com.babbeltest.fallingwords.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by : Pratham
 */
class HomeViewModel @Inject constructor(application: App) : BaseViewModel(application) {

    fun onStartClicked(){
        baseNavigation.value = BaseNavigation.NavigateTo(
            directions = HomeFragmentDirections.actionNavigateHomeToGame()
        )
    }
}