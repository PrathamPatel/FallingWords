package com.babbeltest.fallingwords.util

import android.app.Activity
import android.util.DisplayMetrics

/**
 * Created by : Pratham
 */
object DeviceUtil {

    fun getScreenHeight(activity: Activity) : Int{
        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)
        return metrics.heightPixels
    }
}