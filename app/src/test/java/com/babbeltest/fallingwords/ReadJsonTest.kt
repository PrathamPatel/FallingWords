package com.babbeltest.fallingwords

import com.babbeltest.fallingwords.helper.ReadJson
import com.babbeltest.fallingwords.models.WordDataItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Created by : Pratham
 */
class ReadJsonTest {

    @Test
    fun readJson(){
        val response = ReadJson.readFile("translation_words.json")
        Assert.assertNotNull(response)
    }

    @Test
    fun jsonDeserialisation(){
        val itemConverter = object  : TypeToken<List<WordDataItem>>(){}.type
        val sampleJsonString = "[\n" +
                "  {\n" +
                "    \"text_eng\": \"Hello\",\n" +
                "    \"text_spa\": \"Hola\"\n" +
                "  }\n" +
                "]"

        val expected = listOf(
            WordDataItem(
                textEng = "Hello",
                textSpa = "Hola"))

        val actual = Gson().fromJson<List<WordDataItem>>(sampleJsonString, itemConverter)

        assertEquals(actual, expected)
    }
}