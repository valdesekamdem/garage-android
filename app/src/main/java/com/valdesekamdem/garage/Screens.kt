package com.valdesekamdem.garage

import com.valdesekamdem.garage.core.navigation.Screen
import kotlinx.serialization.Serializable

@Serializable
data object HomeScreen: Screen

@Serializable
data class UserDetailScreen(val name: String): Screen