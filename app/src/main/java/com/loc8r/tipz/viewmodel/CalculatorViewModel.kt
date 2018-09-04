package com.loc8r.tipz.viewmodel

import android.app.Application
import android.databinding.BaseObservable
import android.util.Log
import com.loc8r.tipz.R
import com.loc8r.tipz.model.Calculator
import com.loc8r.tipz.model.TipCalculation

// I'm extending the BaseObservable class so I get the ability to notifyChange() below
// I'm passing in an application so I have access to the dollar_amount string within the strings.xml file
class CalculatorViewModel(val app: Application, val calculator: Calculator = Calculator()): BaseObservable() {
     /**  When creating a viewmodel
     step one is to create variables for each item in the view.
     These are strings not numbers, since the view deals with strings
     instead of numbers,  The viewmodel will host the code that makes
     these sorts of translations.*/

    // First the input variables
    var inputCheckAmount = ""

    var inputTipPrecentage = ""

    // Now the variables needed for outputs.  Here I've created a new
    // object, a TipCalculation that contains all of the needed data
    // this is helpful for view binding
    // var tipCalculation = TipCalculation()

    var outputCheckAmount = ""
    var outputTipAmount = ""
    var outputGrandTotalAmount = ""

    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tc: TipCalculation) {
        outputCheckAmount = app.getString(R.string.dollar_amount, tc.checkAmount)
        outputTipAmount = app.getString(R.string.dollar_amount, tc.tipAmount)
        outputGrandTotalAmount = app.getString(R.string.dollar_amount, tc.grandTotal)
    }

    // The UI includes a button that, when clicked needs to perform
    // an action.  I'll create a function for that.  This function needs
    // a Calculator.  But because I want to support dependency injection,
    // the dependencies will need to be passed in.
    fun calculateTip(){

        // Log.d("TipZ","Calculate tip function invoked")
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPrecentage.toIntOrNull()

        if(checkAmount != null && tipPct != null){
            // Log.d("TipZ", "check Amt: $checkAmount Tip%: $tipPct")
            updateOutputs(calculator.calculateTip(checkAmount,tipPct))
            clearInput()
        }

    }

    private fun clearInput() {
        inputCheckAmount = "0.00"
        inputTipPrecentage = "0"
        notifyChange()
    }
}