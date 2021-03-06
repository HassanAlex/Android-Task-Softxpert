package com.codestation.android_task_softxpert.ui.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.codestation.android_task_softxpert.data.network.repository.NetworkState
import com.codestation.android_task_softxpert.model.Movie
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(
    private val movieRepository: MoviePageListRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val moviePagedListed: LiveData<PagedList<Movie>> by lazy {
        movieRepository.fetchLiveDataPagedList(compositeDisposable)
    }

    val networkState: LiveData<NetworkState> by lazy {
        movieRepository.getNetworkState()
    }

    fun listsEmpty(): Boolean {
        return moviePagedListed.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}