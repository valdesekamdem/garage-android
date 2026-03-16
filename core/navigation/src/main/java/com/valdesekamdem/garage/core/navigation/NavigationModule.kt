package com.valdesekamdem.garage.core.navigation

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

    @Binds
    abstract fun provideNavigator(realNavigator: RealNavigator): Navigator

    @Binds
    abstract fun provideNavigationEventSource(realNavigator: RealNavigator): NavigationEventSource
}