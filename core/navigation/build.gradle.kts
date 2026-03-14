plugins {
    alias(libs.plugins.garage.android.library)
}

android {
    namespace = "com.valdesekamdem.garage.core.navigation"
}

dependencies {
    implementation(libs.androidx.navigation3.runtime)
}
