package com.norberto.gastoemviagem

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainViewModel: ViewModel() {
    private var mTextViewResult = MutableLiveData<String>()
    var textViewResult = mTextViewResult

    init {
        mTextViewResult.value = "R$ 0,00"
    }

    fun calculate(distance: Float, price: Float, autonomy: Float){
        //realizando o calculo total
        val totalValue = (distance * price) / autonomy

        //formantando a string com 2 casas decimais e setando na interface
        mTextViewResult.value = "R$ ${"%.2f".format(totalValue)}"
    }

    fun validation(distance: String, price: String, autonomy: String): Boolean{
        return (autonomy != "" &&
                autonomy != "0" &&
                distance != "" &&
                price != "")
    }

}