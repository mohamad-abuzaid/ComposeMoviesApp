@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}
apply(from = rootProject.file("gradle/config-properties.gradle"))
apply(from = rootProject.file("gradle/config-android.gradle"))

android {
    namespace = "me.abuzaid.movies.data"
}

dependencies {
    api(project(":domain"))

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.json)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.contentNegotiation)

    implementation(libs.room.runtime)
    implementation(libs.sqlite.bundled)

    implementation(libs.timber)
}