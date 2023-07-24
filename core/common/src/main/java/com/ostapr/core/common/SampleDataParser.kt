package com.ostapr.core.common

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception

object SampleDataParser {

    fun openFileStream(fileName: String, appContext: Context?): InputStream {
        return try {
            // should work on instrumented test from assets folder
            appContext!!.assets.open(fileName)
        } catch (e: Exception) {
            // should work on unit tests from resources folder
            this.javaClass.classLoader?.getResourceAsStream(fileName)!!
        }
    }

    fun <T> readSampleData(clazz: Class<T>, inputStream: InputStream, gson: Gson = Gson()): T {
        val jsonString = readSampleData(inputStream)
        return gson.fromJson(jsonString, clazz)
    }

    fun <T> readSampleDataList(inputStream: InputStream, gson: Gson = Gson()): List<T> {
        val jsonString = readSampleData(inputStream)

        val type = object : TypeToken<List<T>>() {}.type
        return gson.fromJson<Array<T>>(jsonString, type).toList()
    }

    private fun readSampleData(inputStream: InputStream): String {
        val reader = BufferedReader(InputStreamReader(inputStream))
        val content = StringBuilder()

        reader.use { bufferedReader ->
            var line = bufferedReader.readLine()
            while (line != null) {
                content.append(line)
                line = bufferedReader.readLine()
            }
        }

        return content.toString()
    }
}