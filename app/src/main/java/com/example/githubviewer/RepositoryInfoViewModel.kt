package com.example.githubviewer

import androidx.lifecycle.LiveData

class RepositoryInfoViewModel {
    val state: LiveData<State>

    sealed interface State {
        object Loading : State
        data class Error(val error: String) : State

        data class Loaded(val gitHubRepo: Repo, val readmeState: ReadmeState) : State
    }

    sealed interface ReadmeState {
        object Loading : ReadmeState
        object Empty : ReadmeState

        data class Error(val error: String) : ReadmeState
        data class Loaded(val markDown: String) : ReadmeState
    }
}