package com.example.agendajson.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.agendajson.databinding.RecyclerViewBinding
import com.example.agendajson.model.User

class UsuarioAdapter(var contexto: Context) : RecyclerView.Adapter<UsuarioAdapter.MyHolder>() {
    inner class MyHolder(var binding: RecyclerViewBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var lista : ArrayList<User>

    init {
        lista = ArrayList()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyHolder {
        val binding : RecyclerViewBinding = RecyclerViewBinding.inflate(
            LayoutInflater.from(contexto),
            parent,
            false
        )
        return MyHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyHolder,
        position: Int
    ) {
        val user = lista[position]
        holder.binding.nombreRecycler.text = "${user.firstName} ${user.lastName}"
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}

