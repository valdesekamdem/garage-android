package com.valdesekamdem.garage.core.navigation.api

interface Navigator {
    fun goTo(screen: Screen)

    fun getBack()
}
