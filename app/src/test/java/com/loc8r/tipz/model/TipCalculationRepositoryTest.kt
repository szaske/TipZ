package com.loc8r.tipz.model

import android.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TipCalculationRepositoryTest {

    // Because I added android.arch.core:core-testing, this gives me the ability to test liveData
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var repository: TipCalculationRepository

    @Before
    fun setup(){
        repository = TipCalculationRepository()
    }

    @Test
    fun testSaveTipToRepo(){
        val tip = TipCalculation(locationName = "IHOP",
                checkAmount = 100.0,
                tipPct = 20,
                tipAmount = 20.0,
                grandTotal = 120.0)
        repository.saveTipCalculation(tip)

        assertEquals(tip, repository.loadTipCalculationByName(tip.locationName))
    }

    @Test
    fun loadSavedTipsFromRepo(){
        val tip1 = TipCalculation(locationName = "IHOP",
                checkAmount = 100.0,
                tipPct = 20,
                tipAmount = 20.0,
                grandTotal = 120.0)
        val tip2 = TipCalculation(locationName = "Jake's Steaks",
                checkAmount = 300.0,
                tipPct = 10,
                tipAmount = 30.0,
                grandTotal = 330.0)

        repository.saveTipCalculation(tip1)
        repository.saveTipCalculation(tip2)

        // observeForever is a function that auto-starts observing...which is needed in a unit test
        // because we have no lifecycle for this class. It requires a lambda
        repository.loadAllTipCalculations().observeForever {
            tipCalculations -> assertEquals(2,tipCalculations?.size)
        }

    }
}