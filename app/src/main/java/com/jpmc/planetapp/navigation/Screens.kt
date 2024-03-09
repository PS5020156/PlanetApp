package com.jpmc.planetapp.navigation

sealed class Screens(val route: String) {
    data object PlanetsScreen : Screens(route = "planets_screen")
}