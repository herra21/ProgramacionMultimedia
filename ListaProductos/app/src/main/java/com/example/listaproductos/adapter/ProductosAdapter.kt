package com.example.listaproductos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.listaproductos.R
import com.example.listaproductos.databinding.ItemRecyclerProductoBinding
import com.example.listaproductos.model.Producto

class ProductosAdapter(var Lista : ArrayList<Producto>, var contexto: Context): RecyclerView.Adapter<ProductosAdapter.MyHolder>() {
    inner class MyHolder(var binding: ItemRecyclerProductoBinding): RecyclerView.ViewHolder(binding.root)

    // crea el holder (patrón de las filas), utilizando la inner class que a su vez utiliza el XML creado
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyHolder {
        val binding : ItemRecyclerProductoBinding = ItemRecyclerProductoBinding.inflate(
            LayoutInflater.from(contexto), parent, false)

        return MyHolder(binding)
    }

    // buscar en la posición de la lista, mostrando en el holder lo que hay en esa posición -> PINTA cada fila
    override fun onBindViewHolder(
        holder: MyHolder,
        position: Int
    ) {
        val item : Producto = Lista[position]
        holder.binding.nonbreRecycler.text=item.title
        Glide.with(contexto).load(item.image).placeholder(R.drawable.prodcuto).into(holder.binding.imagenRecycler)
    }

    // el numero de elementos que hay en la lista -> cuantas filas se pintan
    override fun getItemCount(): Int {
        return Lista.size
    }
}