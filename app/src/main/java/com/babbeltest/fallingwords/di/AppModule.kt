package com.babbeltest.fallingwords.di

import android.content.Context
import com.babbeltest.fallingwords.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by : Pratham
 */
@Module
class AppModule (private val app: App, private val context: Context) {

    @Provides
    @Singleton
    fun provideApplication() : App = app

    @Provides
    @Singleton
    fun provideContext() : Context = context
}