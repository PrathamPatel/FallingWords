package com.babbeltest.fallingwords.api

import android.content.Context
import com.babbeltest.fallingwords.models.WordDataItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import java.io.IOException

/**
 * Created by : Pratham
 */
class GameAPI (private val context: Context){
    fun getWords() : Single<List<WordDataItem>>{

        val itemConverter = object  : TypeToken<List<WordDataItem>>(){}.type
       return try{
            val jsonObject = context.assets.open("translation_words.json")
                .bufferedReader()
                .use { it.readText() }

           val wordList = Gson().fromJson<List<WordDataItem>>(jsonObject, itemConverter)
            Single.just(wordList)
        }catch (ex : IOException){
            Single.just(emptyList())
        }
    }
}