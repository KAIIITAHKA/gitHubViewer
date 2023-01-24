package com.example.githubviewer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class RepositoriesListViewModel {
    val state: LiveData<State> = MutableLiveData()

    sealed interface State {
        object Loading : State

        data class Loaded(val repos: List<Repo>) : State
        data class Error(val error: String) : State

        object Empty : State
    }
}


class Repo(
    val mname: String
)