package com.kursatdrhistoryapp.cryptocoins.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kursatdrhistoryapp.cryptocoins.model.CoinModel
import com.kursatdrhistoryapp.cryptocoins.service.CoinAPIService
import com.kursatdrhistoryapp.cryptocoins.service.CoinDataBase
import com.kursatdrhistoryapp.cryptocoins.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : BaseViewModel(application) {

    var coinList = MutableLiveData<List<CoinModel>>()

    private var coinAPIService = CoinAPIService()
    private var disposable = CompositeDisposable()
    private var sharedPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 0.5 * 60 * 1000 * 1000L

    fun loadData() {
        val time = sharedPreferences.getTime()
        if(time != null && time != 0L && System.nanoTime() - time < refreshTime) {
            getDataFromSQL()
        } else {
            getDataFromAPI()
        }
    }

    fun getDataFromSQL() {
        launch {
            val coins = CoinDataBase(getApplication()).coinDao().getAllCoins()
            showCoin(coins)
        }
    }

    fun getDataFromAPI() {
        disposable.add(
            coinAPIService.getAPI()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CoinModel>>() {
                    override fun onSuccess(t: List<CoinModel>) {
                        storeSQlite(t)
                    }
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun showCoin(list: List<CoinModel>) {
        coinList.value = list
    }

    private fun storeSQlite(list: List<CoinModel>) {
        launch {
            val dao = CoinDataBase(getApplication()).coinDao()
            dao.deleteAllData()
            val longList = dao.insertAll(*list.toTypedArray())
            var i = 0
            while (i < longList.size) {
                list[i].primaryKey = longList[i].toInt()
                i++
            }
            showCoin(list)
        }

        sharedPreferences.saveTime(System.nanoTime())
    }


}