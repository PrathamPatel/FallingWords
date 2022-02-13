package com.babbeltest.fallingwords.ui.game

import androidx.lifecycle.MutableLiveData
import com.babbeltest.fallingwords.App
import com.babbeltest.fallingwords.base.BaseNavigation
import com.babbeltest.fallingwords.base.BaseViewModel
import com.babbeltest.fallingwords.repositories.WordDataRepository
import javax.inject.Inject

/**
 * Created by : Pratham
 */
class GameViewModel @Inject constructor(
    app: App,
    private val wordDataRepository: WordDataRepository) : BaseViewModel(app) {

    var setWord = MutableLiveData<String>().apply { value = "" }
    var fallingWord = MutableLiveData<String>().apply { value =  ""}
    var scoreTxt = MutableLiveData<String>().apply { value = "0" }

    override fun load() {

    }

    fun onMatchClicked(){

    }

    fun onNoMatchClicked(){

    }

    override fun onBackPressed(){
        baseNavigation.value = BaseNavigation.NavigateTo(directions =
        GameFragmentDirections.actionNavigateGameToHome())
    }
}