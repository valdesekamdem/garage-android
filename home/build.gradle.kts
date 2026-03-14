plugins {
    alias(libs.plugins.garage.android.library)
}

android {
    namespace = "com.valdesekamdem.garage.home"
}

dependencies {
    implementation(project(":core:navigation"))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}