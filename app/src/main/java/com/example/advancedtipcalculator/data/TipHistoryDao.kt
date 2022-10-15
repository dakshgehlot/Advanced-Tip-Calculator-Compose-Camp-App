package com.example.advancedtipcalculator.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TipHistoryDao {

    @Query("SELECT * FROM TipTransactionHistory")
    fun getAll(): LiveData<List<TipHistoryItem>>

    @Insert
    suspend fun insertData(item: TipHistoryItem)

}