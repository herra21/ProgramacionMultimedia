package com.example.agendajson

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.agendajson.databinding.ActivityMainBinding
import com.example.agendajson.model.User
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //1 - Peticion del tipo correcto
        realizarPeticionJSON()

    }

    private fun realizarPeticionJSON() {
        val url = "'https://dummyjson.com/users'"
        val request: JsonObjectRequest = JsonObjectRequest(url, {
            Log.v("conexion", "Conexion correcta, procesando peticion")
            procesarRespuesta(it)
        }, {
            Log.v("conexion", "Error en la conexion con el servidor")
        })
        //2 - Añado la peticion a la pila de Volley
        Volley.newRequestQueue(applicationContext).add(request)
    }

    private fun procesarRespuesta(param : JSONObject){
        val gson = Gson()
        val userArray: JSONArray = param.getJSONArray("users")
        for (i in 0..userArray.length()-1) {
            val userJson: JSONObject = userArray.getJSONObject(i)
            val user: User = gson.fromJson(userJson.toString(), User::class.java)
            Log.v("Conexion", "El nombre de usuario es ${user.firstName} ${user.lastName}")
        }

            // pintar RecyclerView con los usuarios que me vienen del JSON
            // donde cada (fila) tiene el nombre, la imagen y el correo
            // RecyclerView -> xml
            // XML del aspecto de cada fila
            // Logica donde se ubica cada fila -> adapter + viewHolder
            // hay que agregar un metodo de añadir usuario
            // cada vez que se lee del JSON se añade al adapter
            // en este metodo hay que notificar los cambios (notifyDataSetChanged o Inserted)
            // recycler layout manager -> Linear / Grid

        /*
        for (i in 0..userArray.length()-1){
            val userJson: JSONObject = userArray.getJSONObject(i)
            Log.v("Conexion", userJson.toString())
            val nombre = userJson.getString("firstName")
            Log.v("Conexion", nombre)
        }
        Log.v("Conexion", param.toString())
        */
        for (i in 0..userArray.length()-1) {
            val userJson: JSONObject = userArray.getJSONObject(i)
            val user: User = gson.fromJson(userJson.toString(), User::class.java)
            Log.v("Conexion", "El nombre de usuario es ${user.firstName} ${user.lastName}")
            // pintar RecyclerView con los usuarios que me vienen del JSON
                // donde cada (fila) tiene el nombre, la imagen y el correo
            // RecyclerView -> xml
            // XML del aspecto de cada fila
            // Logica donde se ubica cada fila -> adapter + viewHolder
                // hay que agregar un metodo de añadir usuario
                // cada vez que se lee del JSON se añade al adapter
                    // en este metodo hay que notificar los cambios (notifyDataSetChanged o Inserted)
            // recycler layout manager -> Linear / Grid
        }
    }
}