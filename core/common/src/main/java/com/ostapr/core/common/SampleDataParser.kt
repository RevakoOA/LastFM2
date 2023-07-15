package com.ostapr.core.common

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader

object SampleDataParser {

    private const val SAMPLE_DATA_FOLDER_NAME = "sampledata"

    fun <T> readSampleData(
        clazz: Class<T>,
        fileName: String,
        sampleDataFolderName: String = SAMPLE_DATA_FOLDER_NAME
    ): T {
        val jsonString = readSampleData(fileName, sampleDataFolderName)
        return Gson().fromJson(jsonString, clazz)
    }

    fun <T> readSampleDataList(
        fileName: String,
        sampleDataFolderName: String = SAMPLE_DATA_FOLDER_NAME
    ): List<T> {
        val jsonString = readSampleData(fileName, sampleDataFolderName)

        val type = object : TypeToken<List<T>>() {}.type
        return Gson().fromJson<Array<T>>(jsonString, type).toList()
    }

    private fun readSampleData(
        fileName: String,
        sampleDataFolderName: String = SAMPLE_DATA_FOLDER_NAME
    ): String {
        val inputStream =
            javaClass.classLoader?.getResourceAsStream("$sampleDataFolderName/$fileName")
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