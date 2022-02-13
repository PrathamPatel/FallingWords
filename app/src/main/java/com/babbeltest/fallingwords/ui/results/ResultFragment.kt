package com.babbeltest.fallingwords.ui.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.babbeltest.fallingwords.base.BaseFragment
import com.babbeltest.fallingwords.databinding.FragmentResultBinding
import com.babbeltest.fallingwords.di.AppComponent

/**
 * Created by : Pratham
 */
class ResultFragment : BaseFragment<ResultViewModel>() {
    lateinit var binding: FragmentResultBinding
    override val viewModelClass: Class<ResultViewModel> = ResultViewModel::class.java
    override fun inject(appComponent: AppComponent) = appComponent.inject(this)

    val args : ResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentResultBinding.inflate(inflater, container, false)
        with(binding){
            varViewModel = viewModel
            lifecycleOwner = this@ResultFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setResultsData(args.gameScore, args.noResponse, args.incorrectResponse)
    }
}