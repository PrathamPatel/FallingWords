package com.babbeltest.fallingwords.ui.results

import androidx.lifecycle.MutableLiveData
import com.babbeltest.fallingwords.App
import com.babbeltest.fallingwords.base.BaseNavigation
import com.babbeltest.fallingwords.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by : Pratham
 */
class ResultViewModel @Inject constructor(app : App) : BaseViewModel(app) {

    var finalScoreTxt = MutableLiveData<String>().apply { value = "" }
    var statsTxt = MutableLiveData<String>().apply { value = "" }


    fun setResultsData(finalScore : Int, noResponseCount : Int, incorrectCount : Int){

        finalScoreTxt.value = "$finalScore / 10"
        statsTxt.value = "Stats: \n\nNo Response: $noResponseCount \nIncorrect Answers: $incorrectCount"
    }

    fun onQuitClicked(){
        baseNavigation.value = BaseNavigation.NavigateTo(ResultFragmentDirections.actionNavigateGameToHome())
    }

    fun onRestartClicked(){
        baseNavigation.value = BaseNavigation.NavigateTo(ResultFragmentDirections.actionNavigateResultToGame())
    }
}