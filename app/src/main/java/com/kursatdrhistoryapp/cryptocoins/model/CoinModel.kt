package com.kursatdrhistoryapp.cryptocoins.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CoinModel(
    @ColumnInfo(name = "currency")
    val currency: String,
    @ColumnInfo(name = "price")
    val price: String
) {
    @PrimaryKey(autoGenerate = true)
    var primaryKey: Int = 0
}