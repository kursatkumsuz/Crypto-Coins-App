package com.kursatdrhistoryapp.cryptocoins.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kursatdrhistoryapp.cryptocoins.model.CoinModel
import com.kursatdrhistoryapp.cryptocoins.service.CoinAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel(application: Application) : BaseViewModel(application) {

    var coinList = MutableLiveData<List<CoinModel>>()

    private var coinAPIService = CoinAPIService()
    private var disposable = CompositeDisposable()

    fun getDataFromAPI() {
        disposable.add(
            coinAPIService.getAPI()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CoinModel>>(){
                    override fun onSuccess(t: List<CoinModel>) {
                       coinList.value = t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }
}