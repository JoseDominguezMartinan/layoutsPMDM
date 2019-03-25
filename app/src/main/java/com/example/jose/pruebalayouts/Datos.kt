package com.example.jose.pruebalayouts

data class Datos(val titulo : String) {

    // personalizamos to String
    override fun toString(): String {
        return "El título es: $titulo"

    }
}