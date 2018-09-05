package com.loc8r.tipz.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

class TipCalculationRepository {

    // This is where I'll save the tip history in-memory
    private val savedTips = mutableMapOf<String,TipCalculation>()

    fun saveTipCalculation(tc: TipCalculation){
        savedTips[tc.locationName] = tc
    }

    // return type includes a ? because it may return a null
    fun loadTipCalculationByName(locationName: String): TipCalculation? {
        return savedTips[locationName]
    }

    fun loadAllTipCalculations(): LiveData<List<TipCalculation>> {
        val liveData = MutableLiveData<List<TipCalculation>>()

        // this is a simple example that only emits once
        liveData.value = savedTips.values.toList()
        return liveData
    }
}