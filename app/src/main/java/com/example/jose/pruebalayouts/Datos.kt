package com.example.jose.pruebalayouts

data class Datos(val titulo : String) {

    // personalizamos to String, con esto traeremos los datos del wordpress
    override fun toString(): String {
        return "El título es: $titulo"

    }
}