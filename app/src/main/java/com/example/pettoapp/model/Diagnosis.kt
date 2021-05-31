package com.example.pettoapp.model

class Diagnosis(
        var diagnosis: String,
        var cause: String,
        var meds: List<String>,
        var treatment: String,
        var shouldRest: Boolean
) {
}