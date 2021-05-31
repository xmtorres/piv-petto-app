package com.example.pettoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.pettoapp.model.AnimalType
import com.example.pettoapp.model.Patient

class NewPatientsActivity : AppCompatActivity() {

    // FIELDS
    private lateinit var nameInput: EditText
    private lateinit var breedInput: EditText
    private lateinit var ageInput: EditText
    private lateinit var causeInput: EditText
    private lateinit var saveButton: Button
    private lateinit var typeSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_patients)

        //FIELDS
        nameInput  = findViewById(R.id.patient_name)
        breedInput = findViewById(R.id.patient_breed)
        ageInput = findViewById(R.id.patient_age)
        causeInput = findViewById(R.id.patient_cause)
        saveButton = findViewById(R.id.save_button)
        typeSpinner = findViewById(R.id.patient_type)

        initializeSpinner(typeSpinner)

        saveButton.setOnClickListener{
            createPatient()
        }

    }

    override fun onUserInteraction() {
        saveButton.isEnabled = isFormComplete()
    }

    fun isFormComplete(): Boolean{
        return  nameInput.text.toString().isNotBlank() &&
                breedInput.text.toString().isNotBlank() &&
                ageInput.text.toString().isNotBlank() &&
                causeInput.text.toString().isNotBlank()
    }

    fun initializeSpinner(sp: Spinner) {
        val adapter = ArrayAdapter<AnimalType>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            AnimalType.values()
        )

        sp.adapter = adapter
    }

    private fun createPatient(): Patient{
        var patient = Patient(nameInput.text.toString(),
            AnimalType.valueOf(typeSpinner.selectedItem.toString()),
            breedInput.text.toString(),
            ageInput.text.toString().toInt(),
            causeInput.text.toString())

        Log.d("PATIENT SAVED:", patient.toString())

        return patient
    }
}