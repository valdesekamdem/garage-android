import com.valdesekamdem.garage.build.configureKotlinJvm
import com.valdesekamdem.garage.build.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinLibraryConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            apply {
                plugin("org.jetbrains.kotlin.jvm")
            }

            configureKotlinJvm()

            dependencies {
                "testImplementation"(libs.findLibrary("junit").get())
            }
        }
    }
}
