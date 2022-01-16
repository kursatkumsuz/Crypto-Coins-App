package com.kursatdrhistoryapp.cryptocoins.service

import com.kursatdrhistoryapp.cryptocoins.model.CoinModel
import retrofit2.Call
import retrofit2.http.GET

interface CoinAPI {


    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
    //GET
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData(): Call<List<CoinModel>>


}