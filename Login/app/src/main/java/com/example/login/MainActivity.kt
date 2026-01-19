package com.example.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.login.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun acciones(){
        binding.botonLogin.setOnClickListener(this)
        binding.botonRegistrar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            binding.botonLogin.id -> {
                Snackbar.make(v, "Intentando hacer login con los datos ${binding.editMail.text.toString()} y ${binding.editPass.text.toString()}.",
                    Snackbar.LENGTH_SHORT).show()
            }
            binding.botonRegistrar.id -> {
                val perfil: String = binding.spinnerPerfil.selectedItem.toString()
                val recordar: Boolean = binding.checkRecordar.isChecked
                var mensaje: String
                if (recordar) {
                    mensaje = "ha marcado la opción de recordar contraseña."
                } else {
                    mensaje = "no ha marcado la opción de recordar contraseña."
                }

                Snackbar.make(v, "Los datos son ${perfil} y ${mensaje}", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}