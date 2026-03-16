package com.valdesekamdem.garage.build

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

/**
 * This config should be used by android modules (application and library)
 */
fun Project.configureKotlinAndroid(extension: CommonExtension) {
    extension.apply {
        compileSdk {
            version = release(libs.androidCompileSdk) {
                minorApiLevel = libs.androidCompileSdkMinor
            }
        }

        defaultConfig.apply {
            minSdk = libs.androidMinSdk

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions.apply {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        buildFeatures.apply {
            compose = true
        }
    }

    configureKotlin<KotlinAndroidProjectExtension>()
}

/**
 * This config should be used by JVM modules only
 */
fun Project.configureKotlinJvm() {
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    configureKotlin<KotlinJvmProjectExtension>()
}

private inline fun <reified T : KotlinBaseExtension> Project.configureKotlin() {
    configure<T> {
        val compilerOptions = when (this) {
            is KotlinAndroidProjectExtension -> compilerOptions
            is KotlinJvmProjectExtension -> compilerOptions
            else -> error("Project extension not supported yet: $this")
        }

        compilerOptions.apply {
            JvmTarget.JVM_11

            freeCompilerArgs.add("-Xexplicit-backing-fields")
        }
    }
}