package com.example.pettoapp.model

class Diagnosis(
        var diagnosis: String,
        var cause: String,
        var meds: List<String>,
        var treatment: String,
        var shouldRest: Boolean
) {
        override fun toString(): String {
                return "{ diagnosis:${this.diagnosis}, cause:${this.cause}, meds:${this.meds}, treatment:${this.treatment}, shouldRest:${this.shouldRest}  }"
        }
}