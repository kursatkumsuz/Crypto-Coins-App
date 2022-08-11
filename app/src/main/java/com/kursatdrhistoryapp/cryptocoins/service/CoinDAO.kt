package com.kursatdrhistoryapp.cryptocoins.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kursatdrhistoryapp.cryptocoins.model.CoinModel

@Dao
interface CoinDAO {

    @Insert
    suspend fun insertAll(vararg coin : CoinModel) : List<Long>

    @Query("SELECT * FROM CoinModel")
    suspend fun getAllCoins() : List<CoinModel>

    @Query("SELECT * FROM CoinModel WHERE primaryKey = :key")
    suspend fun getCoin(key : Int) : CoinModel

    @Query("DELETE FROM CoinModel")
    suspend fun deleteAllData()
}