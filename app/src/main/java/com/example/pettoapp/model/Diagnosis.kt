package com.example.pettoapp.model

import java.io.Serializable

class Diagnosis(
        var doctor: VeterinaryDoctor,
        var patient: Patient,
        var diagnosis: String,
        var cause: String,
        var meds: String, //Upgrade: List<String> --> upon implementing input with chips
        var treatment: String,
        var shouldRest: Boolean
) : Serializable {
        override fun toString(): String {
                return "{ doctor:${this.doctor}, patient:${this.patient}, diagnosis:${this.diagnosis}, cause:${this.cause}, meds:${this.meds}, treatment:${this.treatment}, shouldRest:${this.shouldRest}  }"
        }
}