package com.jpmc.planetapp.planet_feature_detail.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpmc.planetapp.core.common.Result
import com.jpmc.planetapp.planet_feature_detail.domain.usecase.GetPlanetDetailUseCase
import com.jpmc.planetapp.planet_feature_detail.presentation.state.PlanetDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class PlanetDetailViewModel @Inject constructor(
    private val planetDetailUseCase: GetPlanetDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val mutablePlanetDetail = MutableStateFlow(PlanetDetailState())
    val planetDetailState: StateFlow<PlanetDetailState>
        get() = mutablePlanetDetail.asStateFlow()

    init {
        savedStateHandle.get<String>("id")?.let { id ->
            getPlanetDetail(id)
        }
    }

    @VisibleForTesting
    fun getPlanetDetail(id: String) = planetDetailUseCase(id).onEach {
        when (it) {
            is Result.Failure -> {
                mutablePlanetDetail.value = PlanetDetailState().copy(errorMsg = it.errorMsg)
            }

            is Result.Loading -> {
                mutablePlanetDetail.value = PlanetDetailState().copy(isLoading = true)
            }

            is Result.Success -> {
                mutablePlanetDetail.value = PlanetDetailState().copy(planetDetail = it.data!!)
            }
        }

    }.launchIn(viewModelScope)


}