package com.example.easycashchallenge.ui.main.viewmodel

import android.net.ConnectivityManager
import com.example.easycashchallenge.base.BaseViewModel
import com.example.easycashchallenge.base.LiveDataState
import com.example.easycashchallenge.network.models.CompetitionsResponse
import com.example.easycashchallenge.ui.main.repository.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    connectivityManager: ConnectivityManager,
    private var repository: MainRepository
) : BaseViewModel(connectivityManager) {

    private var dataList = LiveDataState<CompetitionsResponse>()
    private val disposable = CompositeDisposable()

    fun refreshMain(): LiveDataState<CompetitionsResponse> {

        if (!isNetworkAvailable) {
            publishNoInternet(dataList)
            return dataList
        }

        publishLoading(dataList)

        disposable.add(
            repository.getAllCompetitions().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(
                    object : DisposableSingleObserver<CompetitionsResponse>() {
                        override fun onSuccess(response: CompetitionsResponse) {
                            publishResult(dataList, response)
                        }

                        override fun onError(error: Throwable) {
                            publishError(dataList, error)
                        }
                    }
                )
        )

        return dataList
    }

}