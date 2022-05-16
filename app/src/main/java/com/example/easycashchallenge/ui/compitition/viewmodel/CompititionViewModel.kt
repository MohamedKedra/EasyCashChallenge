package com.example.easycashchallenge.ui.compitition.viewmodel

import android.net.ConnectivityManager
import com.example.easycashchallenge.base.BaseViewModel
import com.example.easycashchallenge.base.LiveDataState
import com.example.easycashchallenge.network.models.AllTeamsResponse
import com.example.easycashchallenge.network.models.CompetitionInfoResponse
import com.example.easycashchallenge.ui.compitition.repository.CompetitionRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CompititionViewModel(
    connectivityManager: ConnectivityManager,
    private val repository: CompetitionRepository
) : BaseViewModel(
    connectivityManager
) {

    private var dataList = LiveDataState<AllTeamsResponse>()
    private val disposable = CompositeDisposable()

    fun refreshCompetitionInfo(CompetitionId: Int): LiveDataState<AllTeamsResponse> {

        if (!isNetworkAvailable) {
            publishNoInternet(dataList)
            return dataList
        }

        publishLoading(dataList)

        disposable.add(
            repository.getAllTeams(id = CompetitionId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(
                    object : DisposableSingleObserver<AllTeamsResponse>() {
                        override fun onSuccess(response: AllTeamsResponse) {
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