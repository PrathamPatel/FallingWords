package com.babbeltest.fallingwords.helper

import android.content.Context
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.InputStreamReader

/**
 * Created by : Pratham
 */
object ReadJson {

    @Throws(FileNotFoundException::class)
    fun readFile(filename: String): String {
        val br = BufferedReader(InputStreamReader(FileInputStream("../app/src/main/assets/$filename")))
        val sb = StringBuilder()
        var line: String ?= br.readLine()
        while (line != null) {
            sb.append(line)
            line = br.readLine()
        }
        return sb.toString()
    }
}