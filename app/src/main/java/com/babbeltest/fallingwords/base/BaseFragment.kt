package com.babbeltest.fallingwords.base

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.babbeltest.fallingwords.App.Companion.appComponent
import com.babbeltest.fallingwords.di.AppComponent
import com.babbeltest.fallingwords.di.ViewModelFactory
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by : Pratham
 *
 * Generic BaseFragment that can be used to extend all other fragments
 */
abstract class BaseFragment<VM : BaseViewModel> : Fragment(), ViewModelProvider.Factory{

    abstract val viewModelClass : Class<VM>
    val viewModel : VM by lazy { provideViewModel() }

    @Inject
    lateinit var mProvider: Provider<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(appComponent)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this){
            viewModel.onBackPressed()
        }
        callback.isEnabled
    }

    open fun provideViewModel() : VM{
        return ViewModelProvider(
            owner = this,
            factory = ViewModelFactory(mProvider)
        )[viewModelClass]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.baseNavigation.observe(viewLifecycleOwner, navigationObserver)
        super.onViewCreated(view, savedInstanceState)

    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return mProvider.get() as T
    }

    private val navigationObserver = Observer<BaseNavigation>{ nav ->
        nav.let { transition ->

            when(transition){

                is BaseNavigation.NavigateTo ->{
                    findNavController().navigate(transition.directions)
                }
            }
        }
    }
    abstract fun inject(appComponent: AppComponent)
}