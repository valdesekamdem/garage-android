import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.valdesekamdem.garage.build.androidCompileSdk
import com.valdesekamdem.garage.build.androidCompileSdkMinor
import com.valdesekamdem.garage.build.androidMinSdk
import com.valdesekamdem.garage.build.androidTargetSdk
import com.valdesekamdem.garage.build.configureAndroidCompose
import com.valdesekamdem.garage.build.configureKotlinAndroid
import com.valdesekamdem.garage.build.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidApplicationLibraryConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            applyPlugins()

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig {
                    targetSdk = libs.androidTargetSdk
                }

                configureAndroidCompose(this)
            }
        }
    }

    private fun Project.applyPlugins() {
        apply {
            plugin("com.android.application")
            plugin("org.jetbrains.kotlin.plugin.compose")
            plugin("org.jetbrains.kotlin.plugin.serialization")
            plugin("com.google.devtools.ksp")
            plugin("com.google.dagger.hilt.android")
        }
    }
}
