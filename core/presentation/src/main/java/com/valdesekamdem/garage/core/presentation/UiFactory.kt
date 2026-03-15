package com.valdesekamdem.garage.core.presentation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider

interface UiFactory {
    fun register(scope: EntryProviderScope<NavKey>)
}

class UiFactoryRegistry(
    private vararg val factories: UiFactory,
) {
    val entryProvider: (NavKey) -> NavEntry<NavKey>
        get() = entryProvider {
            factories.forEach { factory -> factory.register(this) }
        }
}