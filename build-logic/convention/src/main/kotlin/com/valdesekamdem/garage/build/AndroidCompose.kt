package com.valdesekamdem.garage.build

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.configureAndroidCompose(extension: CommonExtension) {
    extension.apply {
        buildFeatures.apply {
            compose = true
        }
    }

    dependencies {
        val bom = libs.findLibrary("androidx.compose.bom").get()
        "implementation"(platform(bom))
        "implementation"(libs.findLibrary("androidx.compose.ui").get())
        "implementation"(libs.findLibrary("androidx.compose.ui.graphics").get())
        "implementation"(libs.findLibrary("androidx.compose.ui.tooling.preview").get())

        "androidTestImplementation"(platform(bom))

        "debugImplementation"(libs.findLibrary("androidx.compose.ui.tooling").get())
    }
}
