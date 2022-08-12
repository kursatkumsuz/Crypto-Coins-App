package com.kursatdrhistoryapp.cryptocoins.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.kursatdrhistoryapp.cryptocoins.model.CoinModel
import com.kursatdrhistoryapp.cryptocoins.service.CoinDataBase
import kotlinx.coroutines.launch

class PopUpViewModel(application: Application) : BaseViewModel(application) {
    var selectedCoin = MutableLiveData<CoinModel>()

    fun getFromRoom(key : Int) {
        launch {
            val coin = CoinDataBase(getApplication()).coinDao().getCoin(key)
            selectedCoin.value = coin
        }
    }
}