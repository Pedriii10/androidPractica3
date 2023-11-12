package com.example.practica3

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class SavedData(val imc: Double, val formattedIMC: String, val state: String, val gender: String)

class FileManager(private val context: Context) {

    private val fileName = "data.txt"

    fun saveDataToFile(imc: Double, formattedIMC: String, state: String, gender: String) {
        val fileContent = "${getCurrentDate()} - IMC: $formattedIMC, Estado: $state, Género: $gender\n"
        try {
            val file = File(context.getExternalFilesDir(null), fileName)
            val fileOutputStream = FileOutputStream(file, true)
            fileOutputStream.write(fileContent.toByteArray())
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date())
    }

    fun readDataFromFile(): List<SavedData> {
        val file = File(context.getExternalFilesDir(null), fileName)
        val dataList = mutableListOf<SavedData>()

        if (file.exists()) {
            val lines = file.readLines()
            for (line in lines) {
                val tokens = line.split(" - ")
                if (tokens.size == 2) {
                    val imcToken = tokens[1].substring(tokens[1].indexOf("IMC:") + 4, tokens[1].indexOf(","))
                    val stateToken = tokens[1].substring(tokens[1].indexOf("Estado:") + 8, tokens[1].indexOf(", Género"))
                    val genderToken = tokens[1].substring(tokens[1].indexOf("Género:") + 8)

                    val savedData = SavedData(imcToken.toDouble(), imcToken, stateToken, genderToken)
                    dataList.add(savedData)
                }
            }
        }

        return dataList
    }
}
