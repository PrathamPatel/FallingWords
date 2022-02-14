package com.babbeltest.fallingwords.base

import androidx.navigation.NavDirections

/**
 * Created by : Pratham
 */
sealed class BaseNavigation{

    data class NavigateTo(val directions: NavDirections) : BaseNavigation()
}
