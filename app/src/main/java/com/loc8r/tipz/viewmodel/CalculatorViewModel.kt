package com.loc8r.tipz.viewmodel

import com.loc8r.tipz.model.Calculator
import com.loc8r.tipz.model.TipCalculation

class CalculatorViewModel(val calculator: Calculator = Calculator()) {
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
    var tipCalculation = TipCalculation()

    // The UI includes a button that, when clicked needs to perform
    // an action.  I'll create a function for that.  This function needs
    // a Calculator.  But because I want to support dependency injection,
    // the dependencies will need to be passed in.
    fun calculateTip(){

        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPrecentage.toIntOrNull()

        if(checkAmount != null && tipPct != null){
            tipCalculation = calculator.calculateTip(checkAmount,tipPct)
        }

    }
}