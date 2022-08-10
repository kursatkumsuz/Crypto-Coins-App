package com.kursatdrhistoryapp.cryptocoins.service

import com.kursatdrhistoryapp.cryptocoins.model.CoinModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CoinAPIService {


    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CoinAPI::class.java)

    fun getAPI() : Single<List<CoinModel>> {
        return api.getData()
    }
}