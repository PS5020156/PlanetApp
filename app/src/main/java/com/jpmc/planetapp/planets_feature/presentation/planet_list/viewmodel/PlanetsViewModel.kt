package com.jpmc.planetapp.planets_feature.presentation.planet_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpmc.planetapp.core.common.Result
import com.jpmc.planetapp.planets_feature.domain.usecase.GetPlanetsUseCase
import com.jpmc.planetapp.planets_feature.presentation.planet_list.state.PlanetsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class PlanetsViewModel @Inject constructor(
    private val planetsUseCase: GetPlanetsUseCase
) : ViewModel() {

    private val mutablePlanetsState = MutableStateFlow(PlanetsState())

    val planetsState: StateFlow<PlanetsState>
        get() = mutablePlanetsState.asStateFlow()

    init {
        getAllPlanets()
    }

    @VisibleForTesting
    fun getAllPlanets() = planetsUseCase().onEach { planets ->
        when (planets) {
            is Result.Failure -> {
                mutablePlanetsState.value = PlanetsState(errorMsg = planets.errorMsg!!)
            }

            is Result.Loading -> {
                mutablePlanetsState.value = PlanetsState(isLoading = true)
            }

            is Result.Success -> {
                mutablePlanetsState.value = PlanetsState().copy(
                    data = planets.data!!,
                    isLoading = false
                )
            }
        }
    }.launchIn(viewModelScope)


}