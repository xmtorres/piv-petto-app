package com.example.pettoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.pettoapp.model.AnimalType

class NewPatientsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_patients)

        var spinner: Spinner = findViewById(R.id.patient_type)
        initializeSpinner(spinner)

    }

    fun initializeSpinner(sp: Spinner) {
        val adapter = ArrayAdapter<AnimalType>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            AnimalType.values()
        )

        sp.adapter = adapter
    }
}