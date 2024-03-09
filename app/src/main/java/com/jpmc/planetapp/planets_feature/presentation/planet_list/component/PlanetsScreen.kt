package com.jpmc.planetapp.planets_feature.presentation.planet_list.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jpmc.planetapp.planets_feature.domain.model.Planets
import com.jpmc.planetapp.planets_feature.presentation.planet_list.state.PlanetsState

@Composable
fun PlanetsScreen(
    modifier: Modifier,
    state: PlanetsState,
    onItemClick: (String) -> Unit
) {
    val planets = state.data

    if (state.isLoading) {
        Box(modifier = modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
        }
    } else if (!state.errorMsg.isNullOrEmpty()) {
        Box(modifier = modifier.fillMaxSize()) {
            Text(modifier = modifier.align(Alignment.Center), text = state.errorMsg)
        }
    }

    if (planets.isNotEmpty()) {
        LazyColumn {
            items(planets.size) {
                DrawPlanets(modifier = modifier, planet = planets[it]) { planetId ->
                    onItemClick(planetId)
                }
            }
        }
    }
}

@Composable
fun DrawPlanets(modifier: Modifier, planet: Planets, onItemClick: (String) -> Unit) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                onItemClick(planet.uid)
            }
    ) {

        Card(
            modifier = modifier,
            shape = CardDefaults.shape,
            colors = CardDefaults.cardColors(),
            elevation = CardDefaults.cardElevation()
        ) {

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = planet.uid,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default
                )

                Text(
                    text = ". " + planet.name,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default
                )
            }

            Text(
                text = planet.url,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
                modifier = modifier.padding(8.dp)
            )
        }
    }
}