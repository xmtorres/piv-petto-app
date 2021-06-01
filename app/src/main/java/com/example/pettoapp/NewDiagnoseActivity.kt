package com.example.pettoapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.pettoapp.model.AnimalType
import com.example.pettoapp.model.Diagnosis
import com.example.pettoapp.model.Patient
import com.example.pettoapp.model.VeterinaryDoctor

class NewDiagnoseActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var vets: ArrayList<VeterinaryDoctor>
    private lateinit var patients:ArrayList<Patient>
    private lateinit var doctorSpinner: Spinner
    private lateinit var patientSpinner: Spinner
    private lateinit var dxInput : EditText
    private lateinit var causeInput: EditText
    private lateinit var treatmentInput: EditText
    private lateinit var medsInput: EditText
    private lateinit var shouldRestSwitch: Switch
    private lateinit var saveButton: Button

    //TEST PURPOSE ONLY
    var dummyPatients = arrayListOf(Patient("Mooney", AnimalType.DOG, "Mixed", 2, "Anxiety"),
        Patient("Usa-chan", AnimalType.BUNNY, "White", 2, "Vaccination"))

    override fun onCreate(savedInstanceState: Bundle?) {

        vets = intent.extras?.get("doctors") as ArrayList<VeterinaryDoctor>
        patients = intent.getSerializableExtra("patients") as ArrayList<Patient>

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_diagnose)

        //FIELDS
        doctorSpinner = findViewById(R.id.diagnose_doctor)
        patientSpinner = findViewById(R.id.diagnose_patient)
        dxInput = findViewById(R.id.diagnose_dx)
        medsInput = findViewById(R.id.diagnose_meds)
        causeInput = findViewById(R.id.diagnose_cause)
        treatmentInput = findViewById(R.id.diagnose_treatment)
        shouldRestSwitch = findViewById(R.id.diagnose_rest)
        saveButton = findViewById(R.id.save_diagnose_button)

        initializeDoctorSpinner(doctorSpinner)
        doctorSpinner.onItemSelectedListener = this

        saveButton.setOnClickListener {
            var intent = Intent()
            intent.putExtra("dx", createDx())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }

    override fun onUserInteraction() {
        saveButton.isEnabled = isFormComplete()
    }


    fun onChangedDoctorSelection(){
        initializePatientSpinner(patientSpinner, vets.find { vet -> vet.name == (doctorSpinner.selectedItem.toString()) }!!)
    }

    fun initializePatientSpinner(sp: Spinner, veterinaryDoctor: VeterinaryDoctor ) {

        var currentList = patients.mapNotNull{ patient -> patient.name.takeIf { veterinaryDoctor.patientsType.contains(patient.type) }}
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            currentList
        )

        sp.adapter = adapter
    }

    fun initializeDoctorSpinner(sp: Spinner) {
        var vetsName = vets.map { vet -> vet.name } // TO DO: ADD MORE FILTERS --> Related to amount of patients per day & max capacity of vet
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            vetsName
        )

        sp.adapter = adapter
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        onChangedDoctorSelection()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    fun isFormComplete(): Boolean{
        return  dxInput.text.toString().isNotBlank() &&
                medsInput.text.toString().isNotBlank() &&
                causeInput.text.toString().isNotBlank() &&
                treatmentInput.text.toString().isNotBlank()
    }

    private fun createDx(): Diagnosis{
        var dx = Diagnosis(
            vets.find { vet -> vet.name == doctorSpinner.selectedItem.toString() }!!,
            patients.find { p -> p.name == patientSpinner.selectedItem.toString() }!!,
            dxInput.text.toString(),
            causeInput.text.toString(),
            medsInput.text.toString(),
            treatmentInput.text.toString(),
            shouldRestSwitch.isActivated
        )

        Log.d("DX SAVED:", dx.toString())
        return dx
    }
}