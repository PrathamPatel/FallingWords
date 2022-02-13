package com.babbeltest.fallingwords.di

import com.babbeltest.fallingwords.App
import dagger.Component
import javax.inject.Singleton

/**
 * Created by : Pratham
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: App)
}