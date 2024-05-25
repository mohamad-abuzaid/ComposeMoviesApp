@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
}
apply(from = rootProject.file("gradle/config-properties.gradle"))
apply(from = rootProject.file("gradle/config-android.gradle"))

android {
    namespace = "me.abuzaid.movies.domain"
}

dependencies {
    api(libs.coroutines.core)
    api(libs.coroutines.android)
    api(libs.coroutines.test)

    api(libs.koin.core)
    api(libs.koin.android)
    api(libs.koin.compose)
    api(libs.kotlin.test)

    api(libs.moshi.core)
    api(libs.moshi.kotlin)
    api(libs.moshi.adapters)
    ksp(libs.moshi.codegen)
}