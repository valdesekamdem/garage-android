plugins {
    alias(libs.plugins.garage.android.library)
    alias(libs.plugins.garage.android.library.compose)
}

android {
    namespace = "com.valdesekamdem.garage.feature.feature.home"
}

dependencies {
    implementation(libs.androidx.compose.material3)
    implementation(projects.core.navigation.api)
    implementation(projects.core.presentation)
}