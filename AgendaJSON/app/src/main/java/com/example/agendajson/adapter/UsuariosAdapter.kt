package com.example.agendajson.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.agendajson.R
import com.example.agendajson.databinding.RecyclerViewBinding
import com.example.agendajson.model.User

class UsuariosAdapter (var contexto: Context) : RecyclerView.Adapter<UsuariosAdapter.MyHolder>() {

    private lateinit var lista: ArrayList<User>

    init {
        lista = ArrayList()
    }

    inner class MyHolder(var binding: RecyclerViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyHolder {
        val binding : RecyclerViewBinding = RecyclerViewBinding.inflate(LayoutInflater.from(contexto), parent, false)

        return MyHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyHolder,
        position: Int
    ) {
        val user = lista[position]
        holder.binding.nombreRecycler.text="${user.firstName} ${user.lastName}"
        holder.binding.correoRecycler.text=user.email
        //Glide.with(contexto).load(item.image).placeholder(R.drawable.user).into(holder.binding.imagenRecycler)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun addUser(user: User){
        this.lista.add(user)
        notifyItemInserted(lista.size -1)
    }
}