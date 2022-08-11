package com.kursatdrhistoryapp.cryptocoins.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kursatdrhistoryapp.cryptocoins.model.CoinModel

@Database(entities = arrayOf(CoinModel::class), version = 1)
abstract class CoinDataBase : RoomDatabase() {

    abstract fun coinDao(): CoinDAO

    companion object {
        @Volatile private var instance: CoinDataBase? = null

        private var lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDataBase(context).also {db ->
                instance = db
            }
        }

        private fun makeDataBase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CoinDataBase::class.java,
            "coinDataBase"
        ).build()
    }

}
