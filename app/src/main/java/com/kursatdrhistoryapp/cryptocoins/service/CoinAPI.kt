package com.kursatdrhistoryapp.cryptocoins.service

import com.kursatdrhistoryapp.cryptocoins.model.CoinModel
import io.reactivex.Single
import retrofit2.http.GET

interface CoinAPI {

    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData(): Single<List<CoinModel>>


}