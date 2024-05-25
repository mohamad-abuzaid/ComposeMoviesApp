@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}
apply(from = rootProject.file("gradle/config-properties.gradle"))
apply(from = rootProject.file("gradle/config-android.gradle"))

android {
    namespace = "me.abuzaid.movies.di"
}

dependencies {
    implementation(project(":data"))
}