package com.example.jose.pruebalayouts

import android.app.Activity
import android.os.Bundle
import android.util.Log

import kotlinx.android.synthetic.main.activity_main2.*
import org.jetbrains.anko.*
import java.net.URL
import android.view.View.INVISIBLE
import org.json.JSONArray
import android.view.View.VISIBLE

class Main2Activity : Activity() {
    /**
     * segundo activity donde tendremos un boton que hace una solicitud a wordpress
     */

    // para la etiqueta de los logs
    val LOGTAG = "peticionwp"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Cuando le damos al boton empezamos una corutina


        botonC.setOnClickListener {
            // visualizamos una barra de progreso para informar al usario
            // que estamos haciendo algo en segundo plano
            progressBar.visibility = VISIBLE
            peticionwp()
        }

    }

    /**
     * Hace una peticion a un servidor que utiliza API Rest
     */
    fun peticionwp() {

        doAsync {
            // capturamos los errores de la peticion
            try {
                // peticion a un servidor rest que devuelve un json generico

                val respuesta = URL("http://18.225.36.63/WordPress/?rest_route=/wp/v2/posts").readText()

                // parsing data

                // sabemos que recibimos un array de objetos JSON

                val miJSONArray = JSONArray(respuesta)
                val titulos: MutableList<Datos> = ArrayList()
                // recorremos el Array

                for (jsonIndex in 0..(miJSONArray.length() - 1)) {

                    // creamos el objeto 'misDatos' a partir de la clase 'Datos'

                    // asignamos el valor de 'title' en el constructor de la data class 'Datos'
                    val misDatos = Datos(miJSONArray.getJSONObject(jsonIndex).getJSONObject("title").getString("rendered"))
                    titulos.add(jsonIndex,misDatos)
                    // salida procesada en Logcat
                    Log.d(LOGTAG, misDatos.toString())
                }

                // Accedemos al hilo principal

                uiThread {

                    progressBar.visibility = INVISIBLE

                    // rellenamos nuestro TextView con la respuesta sin procesar

                    cuadroTexto.text = titulos[0].toString()
                    // Log.d(LOGTAG, respuesta)
                    longToast("Request performed")
                }

                // Si algo va mal lo capturamos

            } catch (e: Exception) {
                uiThread {
                    progressBar.visibility = INVISIBLE

                    longToast("Something go wrong: $e")
                }
            }
        }
    }
}
