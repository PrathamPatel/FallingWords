package com.babbeltest.fallingwords.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.babbeltest.fallingwords.base.BaseFragment
import com.babbeltest.fallingwords.databinding.FragmentHomeBinding
import com.babbeltest.fallingwords.di.AppComponent

/**
 * Created by : Pratham
 */
class HomeFragment : BaseFragment<HomeViewModel>() {

    lateinit var binding : FragmentHomeBinding
    override val viewModelClass: Class<HomeViewModel> = HomeViewModel::class.java

    override fun inject(appComponent: AppComponent) = appComponent.inject(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

         binding = FragmentHomeBinding.inflate(inflater, container, false)

         with(binding){
            varViewModel = viewModel
            lifecycleOwner = this@HomeFragment
        }

        return binding.root
    }
}