package com.example.advancedtipcalculator.data

import androidx.lifecycle.LiveData

class TipHistoryRepository(private val tipHistoryDao: TipHistoryDao) {

    val readAllData: LiveData<List<TipHistoryItem>> = tipHistoryDao.getAll()

    suspend fun addItem(item: TipHistoryItem){
        tipHistoryDao.insertData(item)
    }
}