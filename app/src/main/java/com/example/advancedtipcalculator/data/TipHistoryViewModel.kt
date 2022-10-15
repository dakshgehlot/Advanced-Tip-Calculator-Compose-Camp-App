package com.example.advancedtipcalculator.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TipHistoryViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<TipHistoryItem>>
    private val repository: TipHistoryRepository

    init {
        val tipHistoryDao = TipDetailsDatabase.getInstance(application).tipHistoryDao()
        repository = TipHistoryRepository(tipHistoryDao)
        readAllData = repository.readAllData
    }

    fun addItem(item: TipHistoryItem){
        viewModelScope.launch(Dispatchers.IO){
            repository.addItem(item)
        }
    }

}