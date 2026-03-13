package com.valdesekamdem.garage.core.navigation

interface Navigator {
    fun goTo(screen: Screen)

    fun getBack()
}
