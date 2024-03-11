package com.jpmc.planetapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jpmc.planetapp.planet_feature_detail.presentation.component.PlanetDetailScreen
import com.jpmc.planetapp.planet_feature_detail.presentation.viewmodel.PlanetDetailViewModel
import com.jpmc.planetapp.planets_feature.presentation.planet_list.component.PlanetsScreen
import com.jpmc.planetapp.planets_feature.presentation.planet_list.state.PlanetsState
import com.jpmc.planetapp.planets_feature.presentation.planet_list.viewmodel.PlanetsViewModel

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.PlanetsScreen.route) {

        composable(route = Screens.PlanetsScreen.route) {
            val planetsViewModel: PlanetsViewModel = hiltViewModel()
            val planetsState: PlanetsState =
                planetsViewModel.planetsState.collectAsStateWithLifecycle().value
            PlanetsScreen(modifier = Modifier, state = planetsState) {
                navController.navigate(Screens.PlanetDetailScreen.route + "/${it}")
            }
        }

        composable(route = Screens.PlanetDetailScreen.route + "/{id}") {
            val planetDetailViewModel = hiltViewModel<PlanetDetailViewModel>()
            val planetDetailState =
                planetDetailViewModel.planetDetailState.collectAsStateWithLifecycle().value
            PlanetDetailScreen(modifier = Modifier, state = planetDetailState)
        }

    }
}