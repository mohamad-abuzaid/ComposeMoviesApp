import java.io.FileInputStream
import java.util.Properties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
}

////////////////////////////////////////////////////////////////////////////////////////////////////
// Config                                                                                         //
////////////////////////////////////////////////////////////////////////////////////////////////////
class BConfig {
    var commonProperties: Properties = Properties()
}

////////////////////////////////////////////////////////////////////////////////////////////////////
// Pull in config properties                                                                      //
////////////////////////////////////////////////////////////////////////////////////////////////////

val config = BConfig()
val basePath = "${rootDir.absolutePath}/config/"

config.commonProperties.load(FileInputStream(rootProject.file("${basePath}common.properties")))

android {
    namespace = "me.abuzaid.movies.data"

    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        all {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL", "\"${config.commonProperties["base_url"]}\"")
            buildConfigField("String", "API_KEY", "\"${config.commonProperties["api"]}\"")
        }
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    api(project(":domain"))

    api(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.contentNegotiation)
    implementation(libs.ktor.client.logging)

    api(libs.room.ktx)
    api(libs.room.runtime)
    ksp(libs.room.compiler)

    implementation(libs.paging.room)

    /****** Test Dependencies ******/
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.paging.common)
}