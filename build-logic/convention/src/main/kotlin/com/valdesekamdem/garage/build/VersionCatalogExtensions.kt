package com.valdesekamdem.garage.build

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

val VersionCatalog.androidCompileSdk: Int
    get() = findVersion("android-compileSdk").get().requiredVersion.toInt()

val VersionCatalog.androidCompileSdkMinor: Int
    get() = findVersion("android-compileSdkMinor").get().requiredVersion.toInt()

val VersionCatalog.androidMinSdk: Int
    get() = findVersion("android-minSdk").get().requiredVersion.toInt()
