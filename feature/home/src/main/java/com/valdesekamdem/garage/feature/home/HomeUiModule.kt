package com.valdesekamdem.garage.feature.home

import com.valdesekamdem.garage.core.presentation.UiFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityComponent::class)
abstract class HomeUiModule {

    @IntoSet
    @Binds
    abstract fun provideHomeUiFactory(factory: HomeUiFactory): UiFactory
}