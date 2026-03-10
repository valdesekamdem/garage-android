package com.valdesekamdem.garage

interface Screen

data object HomeScreen: Screen

data class UserDetailScreen(val name: String): Screen