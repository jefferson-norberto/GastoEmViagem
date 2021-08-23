package com.norberto.gastoemviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalculate.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        //pegando o id do botão da interface
        val id = view.id

        //verificando qual botão foi clicado
        if(id == R.id.buttonCalculate){
            calculate()
        }
    }

    //função para realizar o calculo do preço da viagem
    private fun calculate(){
        //para validar as entradas foi criado uma função
        if(validationOk()) {
            //usa-se o try catch para validar por erros
            try {
                //recuperando os valores digitados na interface dos editstext
                val distance = editTextDistance.text.toString().toFloat()
                val price = editTextPrice.text.toString().toFloat()
                val autonomy = editTextAutonomy.text.toString().toFloat()

                //realizando o calculo total
                val totalValue = (distance * price) / autonomy

                //formantando a string com 2 casas decimais e setando na interface
                textViewResult.text = "R$ ${"%.2f".format(totalValue)}"

                // como estou tratando erro de um npumero específico uso o NumberFormatException
            }catch (nfe: NumberFormatException){
                Toast.makeText(this, getString(R.string.informeValorValido), Toast.LENGTH_LONG).show()
            }
        }else {
            //aqui this é o mesmo que applicationContext
            Toast.makeText(this, getString(R.string.preenchaOsCampos), Toast.LENGTH_LONG).show()
        }
    }

    //função usada para verificar se os campos são diferentes de vazio
    private fun validationOk(): Boolean {
        //Não preciso usar if neste caso pois essa verificação em kotlin já retorna true ou false
        return (editTextAutonomy.text.toString() != "" &&
                editTextAutonomy.text.toString() != "0" &&
                editTextDistance.text.toString() != "" &&
                editTextPrice.text.toString() != "")
    }
}