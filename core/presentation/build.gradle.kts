plugins {
    alias(libs.plugins.garage.android.library)
}

android {
    namespace = "com.valdesekamdem.garage.core.presentation"
}

dependencies {
    implementation(projects.core.navigation)
    implementation(libs.androidx.lifecycle.runtime.compose)
}
