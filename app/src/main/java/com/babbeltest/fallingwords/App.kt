package com.babbeltest.fallingwords

import android.app.Application
import android.content.Context
import com.babbeltest.fallingwords.di.AppComponent
import com.babbeltest.fallingwords.di.AppModule
import com.babbeltest.fallingwords.di.DaggerAppComponent

/**
 * Created by : Pratham
 */
class App : Application(){

    companion object{
        lateinit var appComponent : AppComponent
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        initialiseDagger(base!!)
    }

    private fun initialiseDagger(context: Context){
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this, context))
            .build()

        appComponent.inject(this)
    }
}