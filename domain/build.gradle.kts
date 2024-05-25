@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
}

android {
    namespace = "me.abuzaid.movies.domain"

    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            ndk {
                debugSymbolLevel = "FULL"
            }
        }

        create("staging") {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            matchingFallbacks += listOf("release", "debug")
        }

        getByName("debug") {
            extra["enableCrashlytics"] = false
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

    implementation(libs.timber)
}