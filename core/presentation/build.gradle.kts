plugins {
    alias(libs.plugins.garage.android.library)
}

android {
    namespace = "com.valdesekamdem.garage.core.presentation"
}

dependencies {
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.navigation3.runtime)
}
