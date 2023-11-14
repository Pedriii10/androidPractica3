package com.example.practica3

import YourFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.practica3.Class.Imc
import com.example.practica3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var history: MutableList<Imc> = mutableListOf()
    private var fragment: YourFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calcButton.setOnClickListener {
            if (isFieldsValid()) {
                saveDataAndCalculateIMC()
            }
        }

        fragment = supportFragmentManager.findFragmentById(R.id.main_activity) as? YourFragment

        binding.calcButton.setOnClickListener {
            if (isFieldsValid()) {
                saveDataAndCalculateIMC()
                fragment?.updateHistory(history)
            }
        }

        binding.historicButton.setOnClickListener {
            val fragment = YourFragment.newInstance()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.main_activity, fragment)
                addToBackStack(null)
                commit()
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

    fun getHistory(): MutableList<Imc> {
        return history
    }
}