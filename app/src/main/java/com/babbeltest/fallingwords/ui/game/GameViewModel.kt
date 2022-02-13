package com.babbeltest.fallingwords.ui.game

import android.annotation.SuppressLint
import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.babbeltest.fallingwords.App
import com.babbeltest.fallingwords.Const.CORRECT_MATCH
import com.babbeltest.fallingwords.Const.INCORRECT_MATCH
import com.babbeltest.fallingwords.base.BaseNavigation
import com.babbeltest.fallingwords.base.BaseViewModel
import com.babbeltest.fallingwords.repositories.WordDataRepository
import com.babbeltest.fallingwords.util.DeviceUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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

    private var _hasGameStarted = MutableLiveData<Boolean>().apply { value = false }
    val hasGameStarted : LiveData<Boolean> get() = _hasGameStarted

    private var _isGameOver = MutableLiveData<Boolean>().apply { value = false }
    val isGameOver : LiveData<Boolean> get() = _isGameOver

    var scoreCount = 0
    var wordCount = 0

    var noResponseCount = 0
    var incorrectCount = 0

    var isMatching = false
    var isFromNoResponse = false

    @SuppressLint("CheckResult")
    override fun load() {

        if(wordCount == 10){
            _isGameOver.value = true
        }
        else{
            wordCount++
            isFromNoResponse = false
            wordDataRepository.getWords()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result ->

                    if(!result.isNullOrEmpty()){
                        if(isMatchingTranslation()){
                            val randomWordItem = result[result.indices.random()]
                            setWord.value = randomWordItem.textEng
                            fallingWord.value = randomWordItem.textSpa
                            isMatching = true
                        }else{

                            val randomEnglishWord = result[result.indices.random()].textEng
                            val randomSpanishWord = result[result.indices.random()].textSpa
                            setWord.value = randomEnglishWord
                            fallingWord.value = randomSpanishWord
                            isMatching = false
                        }
                        _hasGameStarted.value = true
                    }else{
                        onBackPressed()
                    }
                }
        }
    }

    fun transitionToResults(){
        baseNavigation.value = BaseNavigation.NavigateTo(
            directions = GameFragmentDirections.actionNavigateGameToResult(scoreCount, noResponseCount, incorrectCount))
    }

    fun setNoResponse(noResponse : Boolean){
        noResponseCount++
        isFromNoResponse = noResponse
    }

    fun onMatchClicked(){
        _hasGameStarted.value = false
        if(isMatching){
            scoreCount++
        }else{
            incorrectCount++
        }

        scoreTxt.value = scoreCount.toString()
        load()
    }

    fun onNoMatchClicked(){
        _hasGameStarted.value = false

        if(!isMatching && !isFromNoResponse){
            scoreCount++
        }
        else if(isMatching && !isFromNoResponse){
            incorrectCount++
        }
        scoreTxt.value = scoreCount.toString()
        load()
    }

    fun getScreenHeight(activity: Activity) = DeviceUtil.getScreenHeight(activity)

    private fun isMatchingTranslation() = (CORRECT_MATCH..INCORRECT_MATCH).random() == CORRECT_MATCH

    override fun onBackPressed(){
        baseNavigation.value = BaseNavigation.NavigateTo(directions =
        GameFragmentDirections.actionNavigateGameToHome())
    }
}