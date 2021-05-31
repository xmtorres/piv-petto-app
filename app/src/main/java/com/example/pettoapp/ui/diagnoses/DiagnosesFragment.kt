package com.example.pettoapp.ui.diagnoses

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pettoapp.NewDiagnoseActivity
import com.example.pettoapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DiagnosesFragment : Fragment() {

    private lateinit var diagnosesViewModel: DiagnosesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        diagnosesViewModel =
                ViewModelProvider(this).get(DiagnosesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_diagnoses, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        diagnosesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        var newDiagnoseButton: FloatingActionButton = root.findViewById(R.id.newdiagnose)

        newDiagnoseButton.setOnClickListener {view ->
            toNewDiagnoseActivity(view)
        }
        return root
    }

    fun toNewDiagnoseActivity(view: View){
        val intent = Intent(view.context, NewDiagnoseActivity::class.java)
        startActivity(intent)
    }
}