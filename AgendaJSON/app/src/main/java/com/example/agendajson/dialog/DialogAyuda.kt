package com.example.agendajson.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DialogAyuda: DialogFragment() {

    private lateinit var opciones: Array<CharSequence>
    private var listaOpciones = arrayListOf<String>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        opciones = arrayOf("Opcion 1", "Opcion 2", "Opcion 3")
    }



    // onAttach -> se pega a la pantalla
    // onCreateDialog -> junta logica + grafica
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Ayuda")
        // builder.setMessage("Esta app muestra datos de un JSON API REST")
        /*builder.setItems(opciones, {_,position ->
            Log.v("dialogo", "pulsado posicion ${opciones[position]}")
        })*/
        /*
        builder.setSingleChoiceItems(opciones,
            -1,
            {_, position ->
                Log.v("dialogo", "pulsado posicion ${opciones[position]}")
            }
        )
         */
        builder.setMultiChoiceItems(opciones, null,
            {_, position, check ->
                if (check) {
                    listaOpciones.add(opciones[position].toString())
                }else {
                    listaOpciones.remove(opciones[position].toString())
                }
            }
        )
        builder.setPositiveButton(
            "Ok", {_, _ ->
                Log.v("dialogo", "pulsado el boton ok")
                Log.v("dialogo", "Los seleccionados son ${listaOpciones}")
            })
        builder.setNegativeButton("No", {_, _ ->
            Log.v("dialogo", "pulsado el boton no")
        })
        builder.setNeutralButton("Cancel", {_, _ ->
            Log.v("dialogo", "pulsado el boton cancel")
        })
        return builder.create()
    }
    // onDetach -> despegar de la pantalla
}