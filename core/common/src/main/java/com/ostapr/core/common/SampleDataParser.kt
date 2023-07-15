package com.ostapr.core.common

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

object SampleDataParser {

    fun <T> readSampleData(clazz: Class<T>, inputStream: InputStream): T {
        val jsonString = readSampleData(inputStream)
        return Gson().fromJson(jsonString, clazz)
    }

    fun <T> readSampleDataList(inputStream: InputStream): List<T> {
        val jsonString = readSampleData(inputStream)

        val type = object : TypeToken<List<T>>() {}.type
        return Gson().fromJson<Array<T>>(jsonString, type).toList()
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