package com.babbeltest.fallingwords.di

import com.babbeltest.fallingwords.App
import com.babbeltest.fallingwords.ui.game.GameFragment
import com.babbeltest.fallingwords.ui.game.GameViewModel
import com.babbeltest.fallingwords.ui.home.HomeFragment
import com.babbeltest.fallingwords.ui.home.HomeViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by : Pratham
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: App)
    fun inject(homeFragment: HomeFragment)
    fun inject(homeViewModel: HomeViewModel)
    fun inject(gameFragment: GameFragment)
    fun inject(gameViewModel: GameViewModel)
}