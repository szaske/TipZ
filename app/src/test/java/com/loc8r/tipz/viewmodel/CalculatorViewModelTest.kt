package com.loc8r.tipz.viewmodel

import android.app.Application
import com.loc8r.tipz.R
import com.loc8r.tipz.model.Calculator
import com.loc8r.tipz.model.TipCalculation
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * The viewmodel has one function, which validates the inputs to the types
 * required by the model, then calculates the tip and sets the output variable
 */
class CalculatorViewModelTest {
    lateinit var calculatorViewModel: CalculatorViewModel

    // We need to create a mock calculator, so the test is only testing the viewmodel
    @Mock lateinit var mockCalculator: Calculator
    @Mock lateinit var mockApp: Application

    @Before
    fun setup(){
        // This initializes any mocks annotated with @Mock
        MockitoAnnotations.initMocks(this)
        stubApplicationResource(0.0,"$0.00")
        calculatorViewModel = CalculatorViewModel(mockApp, mockCalculator)
    }

    // A helper function to mock our fake Calc
    private fun stubTipCalc(tipCalculation: TipCalculation){
        `when`(mockCalculator.calculateTip(10.00,15)).thenReturn(tipCalculation)
    }

    // A helper function to mock our Application
    private fun stubApplicationResource(input: Double, output: String){
        `when`(mockApp.getString(R.string.dollar_amount, input)).thenReturn(output)
    }

    @Test
    fun calculatesTipCorrectly(){
        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPrecentage = "15"

        // Create a mock response from the mock Calc
        val stubResponse = TipCalculation(checkAmount = 10.00,tipAmount = 1.5, grandTotal = 11.5)
        stubTipCalc(stubResponse)

        // Create a mock response from my mock App
        stubApplicationResource(10.0,"$10.00")
        stubApplicationResource(1.5,"$1.50")
        stubApplicationResource(11.5,"$11.50")

        calculatorViewModel.calculateTip()

        // This previous version tests that the viewModel
        assertEquals("$10.00", calculatorViewModel.outputCheckAmount)
        assertEquals("$1.50", calculatorViewModel.outputTipAmount)
        assertEquals("$11.50", calculatorViewModel.outputGrandTotalAmount)
    }

    @Test
    fun doesntCalculateBadTipPercent(){
        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPrecentage = ""
        calculatorViewModel.calculateTip()

        verify(mockCalculator,never()).calculateTip(anyDouble(), anyInt())
    }
}