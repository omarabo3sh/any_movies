plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.dagger.hilt.android") version "2.56.1" apply false
    // Add this line to make KSP available to all modules
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
}
buildscript {
    repositories {
        google()
    }
    dependencies {
        val nav_version = "2.8.9"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}