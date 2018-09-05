package com.loc8r.tipz.model

import android.arch.lifecycle.LiveData
import java.math.RoundingMode

class Calculator constructor(val repository: TipCalculationRepository = TipCalculationRepository()) {
    fun calculateTip(checkAmount: Double, tipPctInput: Int): TipCalculation {

        // rounding code added
        // see: https://stackoverflow.com/questions/49011924/round-double-to-1-decimal-place-kotlin-from-0-044999-to-0-1
        val tipAmount = (checkAmount * (tipPctInput.toDouble() / 100.0 ))
                .toBigDecimal()
                .setScale(2, RoundingMode.HALF_UP)
                .toDouble()

        val grandTotal = checkAmount + tipAmount

        return TipCalculation(
                checkAmount = checkAmount,
                tipPct = tipPctInput,
                tipAmount =  tipAmount,
                grandTotal =  grandTotal)
    }

    fun saveTipCalculation(tc: TipCalculation){
        repository.saveTipCalculation(tc)
    }

    fun loadTipCalculationByLocationName(locationName: String): TipCalculation? {
        return repository.loadTipCalculationByName(locationName)
    }

    fun loadAllTipCalculations(): LiveData<List<TipCalculation>>{
        return repository.loadAllTipCalculations()
    }
}