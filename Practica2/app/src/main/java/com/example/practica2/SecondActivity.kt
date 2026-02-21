package com.example.practica2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica2.databinding.ActivitySecondBinding
import com.example.practica2.model.Carrito
import com.example.practica2.model.ProductoAdapter
import com.google.android.material.snackbar.Snackbar

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var adapter: ProductoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RecyclerView
        adapter = ProductoAdapter(Carrito.lista) { }
        binding.recyclerCarrito.layoutManager = LinearLayoutManager(this)
        binding.recyclerCarrito.adapter = adapter

        actualizarTotal()
        setSupportActionBar(binding.toolbarSecond)
    }

    private fun actualizarTotal() {
        val total = Carrito.lista.sumOf { it.precio }
        binding.tvTotal.text = "Total: %.2f€".format(total)
    }

    // 🔹 Menú
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_carrito, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.menu_confirmar -> {
                val total = Carrito.lista.sumOf { it.precio }
                Snackbar.make(binding.root,
                    "Enhorabuena, compra por valor de %.2f€ realizada".format(total),
                    Snackbar.LENGTH_LONG).show()
                return true
            }

            R.id.menu_vaciar -> {
                Carrito.lista.clear()
                adapter.notifyDataSetChanged()
                actualizarTotal()
                Snackbar.make(binding.root,
                    "Carrito vaciado",
                    Snackbar.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}