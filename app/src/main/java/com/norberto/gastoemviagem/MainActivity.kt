package com.norberto.gastoemviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.textViewResult.observe(this, Observer {
            textViewResult.text = it
        })

        buttonCalculate.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        //pegando o id do botão da interface
        val id = view.id

        //verificando qual botão foi clicado
        if(id == R.id.buttonCalculate){
            //pegando os valores da interface
            val distance = editTextDistance.text.toString()
            val price = editTextPrice.text.toString()
            val autonomy = editTextAutonomy.text.toString()

            //usando a viewModel
            if(viewModel.validation(distance, price, autonomy)){
                viewModel.calculate(distance.toFloat(), price.toFloat(), autonomy.toFloat())
            }else{
                Toast.makeText(this, getString(R.string.preenchaOsCampos), Toast.LENGTH_LONG).show()
            }

        }
    }


}