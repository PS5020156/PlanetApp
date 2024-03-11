package com.jpmc.planetapp.planets_feature.presentation.planet_list.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import com.jpmc.planetapp.MainActivity
import org.junit.Rule
import org.junit.Test

class PlanetsScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun shouldDisplayPlanets() {
        composeRule.apply {
            onNodeWithTag("planetsList").assertIsDisplayed()
        }
    }

    @Test
    fun myUIComponentTest() {
        composeRule.apply {
            onNodeWithText("Kamino", useUnmergedTree = true).performScrollTo()
        }

    }


}