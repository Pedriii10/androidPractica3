package com.example.practica3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import com.example.practica3.databinding.ActivityMainBinding
import com.example.practica3.FileManager


private const val WEIGHT_EXTRA = "weight"
private const val HEIGHT_EXTRA = "height"
private const val GENDER_EXTRA = "gender"
private var weight: Double = 0.0
private var height: Double = 0.0
private var gender: String = ""



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fileManager: FileManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fileManager = FileManager(this)

        binding.calcButton.setOnClickListener {
            if (isFieldsValid()) {
                saveDataAndCalculateIMC()
            }
        }
    }

    private fun isFieldsValid(): Boolean {
        if (TextUtils.isEmpty(binding.heightText.text.toString()) ||
            TextUtils.isEmpty(binding.weightText.text.toString()) ||
            (!binding.genderOptionMale.isChecked && !binding.genderOptionFemale.isChecked)
        ) {
            binding.textError.text = "Datos sin completar"
            return false
        }
        return true
    }

    private fun getSelectedGenderText(): String {
        return if (binding.genderOptionMale.isChecked) {
            getString(R.string.radioButton_male_name)
        } else {
            getString(R.string.radioButton_female_name)
        }
    }

    private fun saveDataAndCalculateIMC() {
        try {
            val weight = binding.weightText.text.toString().toDouble()
            val height = binding.heightText.text.toString().toDouble()
            val gender = getSelectedGenderText()

            val IMC = calculateIMC(weight, height, gender)
            val formattedIMC = String.format("%.2f", IMC)

            fileManager.saveDataToFile(IMC, formattedIMC, getIMCState(IMC, gender), gender)

            binding.textViewResult.text = formattedIMC

            binding.stateResult.text = getIMCState(IMC, gender)
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
    }

    private fun calculateIMC(weight: Double, height: Double, gender: String): Double {
        return weight / (height * height)
    }

    private fun getIMCState(IMC: Double, gender: String): String {
        return when {
            gender.equals("Male", ignoreCase = true) -> {
                when {
                    IMC < 18.5 -> "Peso inferior al normal"
                    IMC <= 24.9 -> "Normal"
                    IMC <= 29.9 -> "Sobrepeso"
                    else -> "Obesidad"
                }
            }
            else -> {
                when {
                    IMC < 18.5 -> "Peso inferior al normal"
                    IMC <= 23.9 -> "Normal"
                    IMC <= 28.9 -> "Sobrepeso"
                    else -> "Obesidad"
                }
            }
        }
    }
}


