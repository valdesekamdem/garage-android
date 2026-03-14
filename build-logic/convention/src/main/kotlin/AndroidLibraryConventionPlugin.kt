import com.android.build.api.dsl.LibraryExtension
import com.valdesekamdem.garage.build.androidCompileSdk
import com.valdesekamdem.garage.build.androidCompileSdkMinor
import com.valdesekamdem.garage.build.androidMinSdk
import com.valdesekamdem.garage.build.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            applyPlugins()
            configureProject()

            dependencies {
                "implementation"(libs.findLibrary("androidx.core.ktx").get())
                "implementation"(platform(libs.findLibrary("androidx.compose.bom").get()))
                "implementation"(libs.findLibrary("androidx.compose.runtime").get())

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
        }
    }

    private fun Project.configureProject() {
        android().apply {
            compileSdk {
                version = release(libs.androidCompileSdk) {
                    minorApiLevel = libs.androidCompileSdkMinor
                }
            }

            defaultConfig {
                minSdk = libs.androidMinSdk

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }

            buildFeatures {
                compose = true
            }
        }

        kotlin().apply {
            compilerOptions {
                freeCompilerArgs.add("-Xexplicit-backing-fields")
            }
        }
    }

    private fun Project.android(): LibraryExtension {
        return extensions.getByType<LibraryExtension>()
    }

    private fun Project.kotlin(): KotlinAndroidProjectExtension {
        return extensions.getByType<KotlinAndroidProjectExtension>()
    }
}
