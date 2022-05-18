package com.example.easycashchallenge.ui.compitition.viewmodel

import android.net.ConnectivityManager
import com.example.easycashchallenge.base.BaseViewModel
import com.example.easycashchallenge.base.LiveDataState
import com.example.easycashchallenge.network.models.AllTeamsResponse
import com.example.easycashchallenge.network.models.ID
import com.example.easycashchallenge.network.models.Team
import com.example.easycashchallenge.ui.compitition.repository.CompetitionRepository
import com.example.easycashchallenge.ui.main.ui.OnDataInsertedListener
import com.example.easycashchallenge.utils.Status
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CompititionViewModel(
    connectivityManager: ConnectivityManager,
    private val repository: CompetitionRepository
) : BaseViewModel(
    connectivityManager
) {

    private lateinit var onDataInsertedListener: OnDataInsertedListener
    private var dataList = LiveDataState<List<Team>>()
    private val disposable = CompositeDisposable()

    fun setListener(onDataInsertedListener: OnDataInsertedListener) {
        this.onDataInsertedListener = onDataInsertedListener
    }

    fun cacheTeams(teams: List<Team>, id: Int) {

        repository.cacheTeams(teams)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onComplete() {
                    cacheId(ID(id = id))
                }

                override fun onError(e: Throwable) {
                    onDataInsertedListener.onInsert(Status.fail, msg = e.message)
                }

            })
    }

    fun cacheId(id: ID) {
        repository.cacheIds(id)
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

    fun refreshTeams(competitionId: Int): LiveDataState<List<Team>> {

        publishLoading(dataList)

        if (isNetworkAvailable) {

            disposable.add(
                repository.getAllTeams(id = competitionId).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribeWith(
                        object : DisposableSingleObserver<AllTeamsResponse>() {
                            override fun onSuccess(response: AllTeamsResponse) {
                                response.teams?.let { list ->
                                    cacheTeams(list, competitionId)
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
            checkCachedItems(competitionId)
        }
        return dataList
    }

    private fun checkCachedItems(competitionId: Int) {
        repository.getAllCachedIds()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<ID>> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(t: List<ID>) {
                    for (item in t) {
                        if (item.id == competitionId) {
                            repository.getAllCachedTeams()
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(object : SingleObserver<List<Team>> {
                                    override fun onSubscribe(d: Disposable) {

                                    }

                                    override fun onError(e: Throwable) {
                                        publishError(dataList, e)
                                    }

                                    override fun onSuccess(t: List<Team>) {
                                        publishResult(dataList, t)
                                    }
                                })
                            return
                        }
                    }
                    publishNoInternet(dataList)
                }

                override fun onError(e: Throwable) {
                    publishError(dataList, e)
                }
            })
    }
}