package com.example.advancedtipcalculator.data

data class TipUiState(
    val inputBill: String = "",
    val tipPercent: Int = 0,
    val split: Int = 0,
    val finalTotalBill: Double = 0.0,
    val finalOnlyBill: Double = 0.0,
    val finalOnlyTip: Double = 0.0
)