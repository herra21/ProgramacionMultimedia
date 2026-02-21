package com.example.practica2

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.practica2.databinding.ActivityMainBinding
import com.example.practica2.model.Carrito
import com.example.practica2.model.Producto
import com.example.practica2.model.ProductoAdapter
import com.google.android.material.snackbar.Snackbar
import android.view.Menu
import android.view.MenuItem
import android.content.Intent
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listaCategorias: ArrayList<String>
    private val listaProductos = mutableListOf<Producto>()
    private lateinit var adapterRecycler: ProductoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        obtenerCategorias()

        binding.spinnerCategorias.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    val categoria = parent.getItemAtPosition(position).toString()
                    cargarProductosPorCategoria(categoria)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }

        adapterRecycler = ProductoAdapter(listaProductos) { producto ->
            // Acción al pulsar "Añadir"
            Carrito.lista.add(producto)
            Snackbar.make(binding.root, "${producto.nombre} agregado al carrito", Snackbar.LENGTH_SHORT).show()
        }

        binding.recyclerProductos .layoutManager = LinearLayoutManager(this)
        binding.recyclerProductos.adapter = adapterRecycler

        setSupportActionBar(binding.toolbar)

    }
    private fun obtenerCategorias() {

        listaCategorias = ArrayList()

        val url = "https://dummyjson.com/products/categories"
        val queue = Volley.newRequestQueue(this)

        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->

                for (i in 0 until response.length()) {
                    val categoria = response.getJSONObject(i)
                    val nombre = categoria.getString("name")  // Solo el campo "name"
                    listaCategorias.add(nombre)
                }

                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item,
                    listaCategorias
                )

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerCategorias.adapter = adapter
            },
            { error ->
                Snackbar.make(binding.root, "ERROR al cargar las categorías.", Snackbar.LENGTH_SHORT).show()
            }
        )

        queue.add(request)
    }
    private fun cargarProductosPorCategoria(categoria: String) {
        val categoriaUrl = categoria.lowercase().replace(" ", "-")
        val url = "https://dummyjson.com/products/category/$categoriaUrl"

        val queue = Volley.newRequestQueue(this)

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val productosArray = response.getJSONArray("products")
                listaProductos.clear()
                for (i in 0 until productosArray.length()) {
                    val p = productosArray.getJSONObject(i)
                    listaProductos.add(
                        Producto(
                            nombre = p.getString("title"),
                            precio = p.getDouble("price"),
                            imagen = p.getString("thumbnail")
                        )
                    )
                }
                adapterRecycler.notifyDataSetChanged()
            },
            { error ->
                Snackbar.make(binding.root, "Error cargando productos", Snackbar.LENGTH_SHORT)
                    .show()
            }
        )
        queue.add(request)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.menu_ver_carrito -> {
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}