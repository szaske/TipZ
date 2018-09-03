package com.loc8r.tipz.model

import java.math.RoundingMode

class RestaurantCalculator {
    fun calculateTip(checkAmount: Double, tipPctInput: Int): TipCalculation {

        // rounding code added
        // see: https://stackoverflow.com/questions/49011924/round-double-to-1-decimal-place-kotlin-from-0-044999-to-0-1
        val tipAmount = (checkAmount * (tipPctInput.toDouble() / 100.0 ))
                .toBigDecimal()
                .setScale(2, RoundingMode.HALF_UP)
                .toDouble()

        val grandTotal = checkAmount + tipAmount

        return TipCalculation(
                checkAmount,
                tipPctInput,
                tipAmount,
                grandTotal)
    }
}