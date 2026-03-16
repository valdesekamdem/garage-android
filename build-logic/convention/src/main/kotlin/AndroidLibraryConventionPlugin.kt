import com.android.build.api.dsl.LibraryExtension
import com.valdesekamdem.garage.build.configureKotlinAndroid
import com.valdesekamdem.garage.build.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            applyPlugins()

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)

                defaultConfig {
                    consumerProguardFiles("consumer-rules.pro")
                }

                buildTypes {
                    release {
                        isMinifyEnabled = false

                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                }
            }

            dependencies {
                "implementation"(libs.findLibrary("androidx.core.ktx").get())
                "implementation"(libs.findLibrary("androidx.compose.runtime").get())
                "implementation"(libs.findLibrary("hilt.android").get())
                "implementation"(libs.findLibrary("hilt.ext.viewmodel.compose").get())
                "ksp"(libs.findLibrary("hilt.android.compiler").get())

                "testImplementation"(libs.findLibrary("junit").get())
                "testImplementation"(libs.findLibrary("androidx.junit").get())
            }
        }
    }

    private fun Project.applyPlugins() {
        apply {
            plugin("com.android.library")
            plugin("org.jetbrains.kotlin.plugin.compose")
            plugin("org.jetbrains.kotlin.plugin.serialization")
            plugin("com.google.devtools.ksp")
            plugin("com.google.dagger.hilt.android")
        }
    }
}
