package com.example.advancedtipcalculator.ui

import androidx.lifecycle.ViewModel
import com.example.advancedtipcalculator.data.TipUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CalculatorViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(TipUiState())
    val uiState: StateFlow<TipUiState> = _uiState.asStateFlow()

    fun onUserValueChanged(newValue: String){
        _uiState.update {
            currentState ->
            currentState.copy(
                inputBill = newValue
            )
        }
    }

    fun onSplitIncrease(){
        _uiState.update {
                currentState ->
            currentState.copy(
                split = uiState.value.split + 1
            )
        }
    }

    fun onSplitDecrease(){
        if (uiState.value.split != 0){
            _uiState.update {
                    currentState ->
                currentState.copy(
                    split = uiState.value.split - 1
                )
            }
        }
        println(_uiState.value.split)
    }

    fun setFinalQuantities(){
        _uiState.update {
            currentState ->
            val results = calculateTip(_uiState.value.inputBill, _uiState.value.tipPercent.toDouble(), _uiState.value.split)
            currentState.copy(
                finalTotalBill = results[0],
                finalOnlyBill = results[1],
                finalOnlyTip = results[2]
            )
        }
    }

    private fun calculateTip(bill: String, tipPercent: Double, split: Int): List<Double>{
        val onlyBill = bill.toDoubleOrNull() ?: 0.0
        val onlyTip = tipPercent / 100 * onlyBill
        val totalBillWithTip = onlyBill + onlyTip

        return if (split != 0) {
            val finalBillPerPerson = totalBillWithTip / split
            val billPerPerson = onlyBill / split
            val tipPerPerson = onlyTip / split
            listOf(
                finalBillPerPerson,
                billPerPerson,
                tipPerPerson
            )
        } else {
            listOf(totalBillWithTip, onlyBill, onlyTip)
        }
    }

    fun onTipChange(tip: Int) {
        if (_uiState.value.tipPercent != tip) {
            _uiState.update {
                currentState ->
                currentState.copy(
                    tipPercent = tip
                )
            }
        } else {
            _uiState.update {
                    currentState ->
                currentState.copy(
                    tipPercent = 0
                )
            }
        }
        println(_uiState.value.tipPercent)
    }
}