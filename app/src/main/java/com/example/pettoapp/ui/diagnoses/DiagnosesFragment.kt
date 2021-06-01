package com.example.pettoapp.ui.diagnoses

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pettoapp.MainActivity
import com.example.pettoapp.NewDiagnoseActivity
import com.example.pettoapp.NewPatientsActivity
import com.example.pettoapp.R
import com.example.pettoapp.model.Diagnosis
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DiagnosesFragment : Fragment() {

    private lateinit var diagnosesViewModel: DiagnosesViewModel
    private lateinit var startForResult: ActivityResultLauncher<Intent>
    private lateinit var act: MainActivity
    private lateinit var newDiagnoseButton: FloatingActionButton

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        diagnosesViewModel =
                ViewModelProvider(this).get(DiagnosesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_diagnoses, container, false)
/*
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        diagnosesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
*/
        act = activity as MainActivity

        startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult -> manageResult(result)
        }

        newDiagnoseButton = root.findViewById(R.id.newdiagnose)

        checkPatients()

        return root
    }

    fun manageResult(result: ActivityResult){
        if (result.resultCode == Activity.RESULT_OK) {
            var diag = result.data?.getSerializableExtra("dx") as Diagnosis
            Log.d("PAYLOAD: ", diag.toString())
            act.diagnoses.add(diag)
        } else {
            Toast.makeText(activity, "Couldn't save diagnose!", Toast.LENGTH_SHORT)
        }
    }

    fun toNewDiagnoseActivity(view: View){
        var intent = Intent(activity, NewDiagnoseActivity::class.java)
        intent.putExtra("doctors", act.vets)
        intent.putExtra("patients", act.patients)

        startForResult.launch(intent)
    }

    fun checkPatients(){
        if(act.patients.isNotEmpty()){
            newDiagnoseButton.setOnClickListener {view ->
                toNewDiagnoseActivity(view)
            }
        } else {
            newDiagnoseButton.isEnabled = false
        }
    }
}