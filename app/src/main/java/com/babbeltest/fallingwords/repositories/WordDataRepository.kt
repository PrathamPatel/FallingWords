package com.babbeltest.fallingwords.repositories

import com.babbeltest.fallingwords.api.GameAPI
import com.babbeltest.fallingwords.models.WordDataItem
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by : Pratham
 */
@Singleton
class WordDataRepository @Inject constructor(
    private val gameAPI: GameAPI
){
    fun getWords() : Single<List<WordDataItem>>{
        return gameAPI.getWords()
    }
}