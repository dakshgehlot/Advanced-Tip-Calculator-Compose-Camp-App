package com.example.advancedtipcalculator.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TipHistoryItem::class], version = 1, exportSchema = false)
abstract class TipDetailsDatabase: RoomDatabase() {
    abstract fun tipHistoryDao(): TipHistoryDao

    companion object{
        private var INSTANCE: TipDetailsDatabase? = null

        fun getInstance(context: Context): TipDetailsDatabase{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TipDetailsDatabase::class.java,
                        "TipDetailsHistoryDatabase"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}