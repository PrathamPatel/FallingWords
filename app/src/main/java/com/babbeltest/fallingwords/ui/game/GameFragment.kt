package com.babbeltest.fallingwords.ui.game

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.babbeltest.fallingwords.Const.MAX_DURATION_ANIMATION
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

    private var isAnimationStarted = false

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.load()
        setupObservers()
    }

    private fun setupObservers(){

        viewModel.hasGameStarted.observe(viewLifecycleOwner){
            if(it){
                binding.tvFallingWord.y = 0f
                startWordFallingAnimation()
            }
            else{
                isAnimationStarted = false
            }
        }

        viewModel.isGameOver.observe(viewLifecycleOwner){
            if(it){
                binding.tvFallingWord.clearAnimation()
                binding.tvFallingWord.visibility = View.GONE
                binding.tvSetWord.visibility = View.GONE
                viewModel.transitionToResults()
            }
        }
    }

    private fun startWordFallingAnimation(){
        if(!isAnimationStarted){
            binding.tvFallingWord.animate()
                .apply {
                    duration = MAX_DURATION_ANIMATION
                    translationYBy(viewModel.getScreenHeight(requireActivity()).toFloat())
                        .setListener(animatorListener)
                        .start()
                }
        }
    }

    private val animatorListener = object : Animator.AnimatorListener{
        override fun onAnimationStart(animation: Animator?) {
            isAnimationStarted = true
        }

        override fun onAnimationEnd(animation: Animator?) {
           if(isAnimationStarted){
              isAnimationStarted = false
              viewModel.setNoResponse(noResponse = true)
              viewModel.onNoMatchClicked()
           }
        }

        override fun onAnimationCancel(animation: Animator?) {

        }

        override fun onAnimationRepeat(animation: Animator?) {

        }
    }
}