package com.example.pettoapp.model

class Patient(
        val name: String,
        val type: AnimalType,
        val breed: String,
        var age: Int,
        var cause: String
){
        override fun toString(): String {
                return "{ name:${this.name}, type:${this.type}, breed:${this.breed}, age:${this.age}, cause:${this.cause}  }"
        }
}