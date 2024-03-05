package com.portafoliowebmariano.factschucknorris.ui

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.portafoliowebmariano.factschucknorris.R
import com.portafoliowebmariano.factschucknorris.data.JokesRepository
import com.portafoliowebmariano.factschucknorris.databinding.ActivityMainBinding
import com.portafoliowebmariano.factschucknorris.viewmodel.JokeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val jokesViewModel: JokeViewModel by viewModels()
    private var itemSelectedSearch = ""

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contoller()
        observer()
        initUI()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initUI() {
        jokesViewModel.getCategory()
        initColor()
        jokesViewModel.getRandomJoke("random")
    }

    private fun observer() {
        jokesViewModel.currentJoke.observe(this, Observer { currentJoke ->
            binding.tvFacts.text = currentJoke?.value.toString()
        })

        jokesViewModel.category.observe(this, Observer { categories ->
            val categoriesMutable: MutableList<String> = categories.toMutableList()
            categoriesMutable.add(0, "random")
            val adapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, categoriesMutable)

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.sCategory.adapter = adapter

            Log.i("myTag", categoriesMutable.toString())

            binding.sCategory.setOnItemSelectedListener(object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    // Obtener el elemento seleccionado
                    val itemSelected = categoriesMutable[position]

                    itemSelectedSearch = if (itemSelected == "random") {
                        itemSelected
                    } else {
                        "random?category=$itemSelected"
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Manejar el caso en que no se ha seleccionado ningún elemento
                }
            })


        })
        jokesViewModel.isLoading.observe(this, Observer {
            binding.pbFacts.isVisible = it
        })
    }

    private fun contoller() {
        binding.btnStart.setOnClickListener {
            jokesViewModel.getRandomJoke(itemSelectedSearch)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Cambiar el color de la barra de estado
            window.statusBarColor = resources.getColor(R.color.color_main, theme)
        }
    }
}