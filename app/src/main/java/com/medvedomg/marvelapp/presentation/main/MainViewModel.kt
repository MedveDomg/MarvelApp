package com.medvedomg.marvelapp.presentation.main

import androidx.lifecycle.ViewModel
import com.medvedomg.marvelapp.presentation.util.SingleLiveEvent
import com.medvedomg.marvelapp.presentation.util.asLiveData

class MainViewModel : ViewModel() {

    private val navigationActionMutableLiveData = SingleLiveEvent<Action>()
    val navigationActionLiveData = navigationActionMutableLiveData.asLiveData()

    init {
        navigationActionMutableLiveData.value = Action.StartAction
    }

    sealed class Action {

        object StartAction : Action()
    }
}