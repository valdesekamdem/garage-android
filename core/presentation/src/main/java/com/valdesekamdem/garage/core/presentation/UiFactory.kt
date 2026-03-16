package com.valdesekamdem.garage.core.presentation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import javax.inject.Inject

interface UiFactory {
    fun register(scope: EntryProviderScope<NavKey>)
}

class UiFactoryRegistry @Inject constructor(
    private val factories: Set<@JvmSuppressWildcards UiFactory>
) {
    val entryProvider: (NavKey) -> NavEntry<NavKey>
        get() = entryProvider {
            factories.forEach { factory -> factory.register(this) }
        }
}