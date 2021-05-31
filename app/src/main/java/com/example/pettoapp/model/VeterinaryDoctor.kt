package com.example.pettoapp.model

class VeterinaryDoctor(
        val name: String,
        var patientsType: List<AnimalType>,
        var maxPatients: Int? = null
){
        override fun toString(): String {
                return "{ name:${this.name}, patientsType:${this.patientsType}, maxPatients:${this.maxPatients}  }"
        }
}