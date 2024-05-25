import java.util.Properties
import java.io.FileInputStream

plugins {
    kotlin("android")
    kotlin("parcelize")
}

////////////////////////////////////////////////////////////////////////////////////////////////////
// Config                                                                                         //
////////////////////////////////////////////////////////////////////////////////////////////////////
class Config {
    var commonProperties: Properties = Properties()
    var keystoreProperties: Properties = Properties()
}

////////////////////////////////////////////////////////////////////////////////////////////////////
// Pull in config properties                                                                      //
////////////////////////////////////////////////////////////////////////////////////////////////////

val config = Config()
val basePath = "${rootDir.absolutePath}/config/"

config.commonProperties.load(FileInputStream(rootProject.file("${basePath}common.properties")))
config.keystoreProperties.load(FileInputStream(rootProject.file("${basePath}keystore.properties")))

android{
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            //shrinkResources = true
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            ndk {
                debugSymbolLevel = "FULL"
            }

            buildConfigField("String", "BASE_URL", "\"${config.commonProperties["base_url"]}\"")
            buildConfigField("String", "API_KEY", "\"${config.commonProperties["api"]}\"")
        }

        create("staging") {
            isDebuggable = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("staging")
            matchingFallbacks += listOf("release", "debug")

            buildConfigField("String", "BASE_URL", "\"${config.commonProperties["base_url"]}\"")
            buildConfigField("String", "API_KEY", "\"${config.commonProperties["api"]}\"")
        }

        getByName("debug") {
            isDebuggable = true
            extra["enableCrashlytics"] = false

            buildConfigField("String", "BASE_URL", "\"${config.commonProperties["base_url"]}\"")
            buildConfigField("String", "API_KEY", "\"${config.commonProperties["api"]}\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}
