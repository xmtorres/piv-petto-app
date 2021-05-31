package com.example.pettoapp.ui.patients

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pettoapp.MainActivity
import com.example.pettoapp.NewPatientsActivity
import com.example.pettoapp.R
import com.example.pettoapp.model.Patient
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.Serializable

class PatientsFragment : Fragment() {

    private lateinit var patientsViewModel: PatientsViewModel
    private lateinit var startForResult: ActivityResultLauncher<Intent>
    private lateinit var act: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       patientsViewModel =
                ViewModelProvider(this).get(PatientsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_patients, container, false)

        startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            manageResult(result)
        }

        act = activity as MainActivity

        var newPatientButton: FloatingActionButton = root.findViewById(R.id.newpatient)

        if(act.openSpots > 0)
            newPatientButton.setOnClickListener { view ->
                toNewPatientActivity(view)
            }
        else {
            newPatientButton.isEnabled = false
        }

        return root
    }

    fun manageResult(result: ActivityResult){
        if (result.resultCode == Activity.RESULT_OK) {
            var patient = result.data?.extras?.getSerializable("patient")
            act.patients.add(patient as Patient)
        }
    }

    fun toNewPatientActivity(view: View){
        startForResult.launch(Intent(activity, NewPatientsActivity::class.java))
    }
}