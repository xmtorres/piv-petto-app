package com.example.pettoapp.model

class VeterinaryDoctor(
        val name: String,
        var patientsType: List<AnimalType>,
        var maxPatients: Int? = null
){}