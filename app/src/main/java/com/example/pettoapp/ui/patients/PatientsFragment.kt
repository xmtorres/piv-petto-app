package com.example.pettoapp.ui.patients

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pettoapp.MainActivity
import com.example.pettoapp.NewPatientsActivity
import com.example.pettoapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PatientsFragment : Fragment() {

    private lateinit var patientsViewModel: PatientsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       patientsViewModel =
                ViewModelProvider(this).get(PatientsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_patients, container, false)
/*
        val textView: TextView = root.findViewById(R.id.text_gallery)
        patientsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
 */
        val act: MainActivity = activity as MainActivity

        var newPatientButton: FloatingActionButton = root.findViewById(R.id.newpatient)

        newPatientButton.setOnClickListener { view ->
            toNewPatientActivity(view, act)
        }
        return root
    }

    fun toNewPatientActivity(view: View, activity: MainActivity){
        var data = Bundle()
        data.putInt("open_spots", activity.currentOpenSpots())
        val intent = Intent(view.context, NewPatientsActivity::class.java).putExtra("data", data)
        startActivity(intent)
    }
}