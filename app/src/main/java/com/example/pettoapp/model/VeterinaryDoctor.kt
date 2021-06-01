package com.example.pettoapp.model

import java.io.Serializable

class VeterinaryDoctor(
        val name: String,
        var patientsType: List<AnimalType>,
        var maxPatients: Int? = null
): Serializable{

        override fun toString(): String {
                return "{ name:${this.name}, patientsType:${this.patientsType}, maxPatients:${this.maxPatients}  }"
        }

}