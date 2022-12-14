package com.medvedomg.marvelapp.presentation.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medvedomg.marvelapp.domain.GetDetailsUseCase
import com.medvedomg.marvelapp.domain.util.Result
import com.medvedomg.marvelapp.presentation.util.ViewState
import com.medvedomg.marvelapp.presentation.util.asLiveData
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getDetailsUseCase: GetDetailsUseCase
) : ViewModel() {

    private val _viewStateLiveData =
        MutableLiveData<ViewState<DetailsModel>>(ViewState.Loading)
    val viewStateLiveData = _viewStateLiveData.asLiveData()

    init {
        viewModelScope.launch {
            when (val result = getDetailsUseCase(Unit)) {
                is Result.Success -> {
                    val viewState = ViewState.Success(result.data)
                    _viewStateLiveData.postValue(viewState)
                }
                is Result.Error -> {
                    val viewState = ViewState.Error<DetailsModel>(
                        error = result.error,
                        errorMessage = result.error.message
                    )
                    _viewStateLiveData.postValue(viewState)
                }
            }
        }
    }
}