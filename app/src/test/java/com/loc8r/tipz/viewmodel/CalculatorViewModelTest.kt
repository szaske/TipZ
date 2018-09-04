package com.loc8r.tipz.viewmodel

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
    @Mock
    lateinit var mockCalculator: Calculator

    @Before
    fun setup(){
        // This initializes any mocks annotated with @Mock
        MockitoAnnotations.initMocks(this)
        calculatorViewModel = CalculatorViewModel(mockCalculator)
    }

    private fun stubTipCalc(tipCalculation: TipCalculation){
        `when`(mockCalculator.calculateTip(10.00,15)).thenReturn(tipCalculation)
    }

    @Test
    fun calculatesTipCorrectly(){
        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPrecentage = "15"

        // Create a mock response
        val stubResponse = TipCalculation(10.00,15,1.5,11.5)
        stubTipCalc(stubResponse)

        calculatorViewModel.calculateTip()

        assertEquals(stubResponse, calculatorViewModel.tipCalculation)
    }

    @Test
    fun doesntCalculateBadTipPercent(){
        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPrecentage = ""
        calculatorViewModel.calculateTip()

        verify(mockCalculator,never()).calculateTip(anyDouble(), anyInt())
    }
}