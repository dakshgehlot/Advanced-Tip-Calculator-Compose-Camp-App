package com.example.advancedtipcalculator.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TipTransactionHistory")
data class TipHistoryItem(

    @PrimaryKey(autoGenerate = true)
    var itemId: Int = 0,

    @ColumnInfo(name = "TotalBill")
    val totalBill: Double,

    @ColumnInfo(name = "BillPerPerson")
    val billPerPerson: Double,

    @ColumnInfo(name = "TipPerPerson")
    val tipPerPerson: Double
)
