package com.example.jose.pruebalayouts

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.jetbrains.anko.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var boton=findViewById<Button>(R.id.button)
        var horizontal = findViewById<TextView>(R.id.textView2) // tenemos un elemento boton que solo aparece en la posicion horizontal , sera lo que usaremos para saber la posicion en la que esta y mostrar un toast
        var is_horizontal=false;

        val intent=Intent(this,Main2Activity::class.java).apply {  }

        boton.setOnClickListener{
            if(horizontal!=null) // si el boton existe marcamos la variable a true y mostramos el toast
                is_horizontal=true
            if(is_horizontal)
                toast("horizontal")
            else
            startActivity(intent)

        }



    }
}
