package com.loc8r.tipz.model
/**
 * TipCalculation Model class
 *
 * It does not actually calculate anything
 *
 * Data classes get automatically created equals, hashcode and
 * toString functions as well as componentN functions
 */
data class TipCalculation(
        val checkAmount: Double = 0.0,
        val tipPct: Int = 0,
        val tipAmount: Double = 0.0,
        val grandTotal: Double = 0.0
) {



}