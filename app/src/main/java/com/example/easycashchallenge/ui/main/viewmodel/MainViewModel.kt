package com.example.easycashchallenge.ui.main.viewmodel

import android.net.ConnectivityManager
import com.example.easycashchallenge.base.BaseViewModel
import com.example.easycashchallenge.base.LiveDataState
import com.example.easycashchallenge.network.models.Competition
import com.example.easycashchallenge.network.models.CompetitionsResponse
import com.example.easycashchallenge.ui.main.repository.MainRepository
import com.example.easycashchallenge.ui.main.ui.OnDataInsertedListener
import com.example.easycashchallenge.utils.Status
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    connectivityManager: ConnectivityManager,
    private var repository: MainRepository
) : BaseViewModel(connectivityManager) {

    private var dataList = LiveDataState<List<Competition>>()
    private val disposable = CompositeDisposable()
    private lateinit var onDataInsertedListener: OnDataInsertedListener

    fun setListener(onDataInsertedListener: OnDataInsertedListener) {
        this.onDataInsertedListener = onDataInsertedListener
    }

    fun cacheCompetitions(competitions: List<Competition>) {

        repository.cacheCompetitions(competitions)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onComplete() {
                    onDataInsertedListener.onInsert(Status.success)
                }

                override fun onError(e: Throwable) {
                    onDataInsertedListener.onInsert(Status.fail, msg = e.message)
                }

            })
    }


    fun refreshMain(): LiveDataState<List<Competition>> {

        publishLoading(dataList)

        if (isNetworkAvailable) {

            disposable.add(
                repository.getAllCompetitions().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribeWith(
                        object : DisposableSingleObserver<CompetitionsResponse>() {
                            override fun onSuccess(response: CompetitionsResponse) {
                                response.competitions?.let { list ->
                                    cacheCompetitions(list)
                                    publishResult(dataList, list)
                                }
                            }

                            override fun onError(error: Throwable) {
                                publishError(dataList, error)
                            }
                        }
                    )
            )

        } else {
            repository.getCachedCompetitions()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<Competition>> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onError(e: Throwable) {
                        publishError(dataList, e)
                    }

                    override fun onSuccess(t: List<Competition>) {
                        publishResult(dataList, t)
                    }
                })
        }

        return dataList
    }

}