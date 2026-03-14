plugins {
    alias(libs.plugins.garage.android.library)
}

android {
    namespace = "com.valdesekamdem.garage.core.navigation"
}

dependencies {
    api(libs.androidx.navigation3.runtime)
}
