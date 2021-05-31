package com.example.pettoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class NewDiagnoseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        //TO DELETE
        val tempDoctorList = arrayListOf("John", "Peter")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_diagnose)
        var doctorSpinner: Spinner = findViewById(R.id.diagnose_doctor)
        var patientSpinner: Spinner = findViewById(R.id.diagnose_patient)

        //TO CHANGE
        initializePatientSpinner(patientSpinner)
        initializeDoctorSpinner(doctorSpinner, tempDoctorList)
    }

    fun initializePatientSpinner(sp: Spinner) {

        //TO DELETE
        var array = arrayOf("Poochie","Daisy", "Pup")
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            array
        )

        sp.adapter = adapter
    }

    fun initializeDoctorSpinner(sp: Spinner, list: List<String>) {
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            list
        )

        sp.adapter = adapter
    }
}