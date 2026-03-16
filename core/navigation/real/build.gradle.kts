plugins {
    alias(libs.plugins.garage.android.library)
}

android {
    namespace = "com.valdesekamdem.garage.core.navigation.real"
}

dependencies {
    api(projects.core.navigation.api)
}