package com.babbeltest.fallingwords.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.babbeltest.fallingwords.base.BaseFragment
import com.babbeltest.fallingwords.databinding.FragmentGameBinding
import com.babbeltest.fallingwords.di.AppComponent

/**
 * Created by : Pratham
 */
class GameFragment : BaseFragment<GameViewModel>() {
    private lateinit var binding : FragmentGameBinding
    override val viewModelClass: Class<GameViewModel> = GameViewModel::class.java
    override fun inject(appComponent: AppComponent) = appComponent.inject(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentGameBinding.inflate(inflater, container, false)
        with(binding){
            varViewModel = viewModel
            lifecycleOwner = this@GameFragment
        }

        return binding.root
    }
}