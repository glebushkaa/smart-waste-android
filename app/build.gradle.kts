plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.secrets.gradle)
    kotlin("kapt")
}

android {
    namespace = "ua.glebm.smartwaste"
    compileSdk = 34

    defaultConfig {
        applicationId = "ua.glebm.smartwaste"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
        }
        release {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = "1.5.3"

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.material)
    implementation(libs.compose.constraint.layout)
    implementation(libs.compose.navigation)
    implementation(libs.material)
    implementation(libs.androidx.activity.compose)
    implementation(libs.maps.compose)
    implementation(libs.play.services.maps)
    implementation(libs.maps.compose.utils)
    implementation(libs.maps.compose.widgets)
    implementation(libs.play.services.location)

    implementation(libs.lifecycle.runtime.compose)

    implementation(libs.coil.compose)

    implementation(libs.androidx.data.store)

    implementation(libs.androidx.appcompat)
    implementation(libs.timber)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.retrofit.gson.converter)
    implementation(libs.retrofit)
    implementation(libs.okhttp3.logging.interceptor)
    implementation(libs.okhttp3)
    implementation(libs.gson)

    implementation(libs.glide)

    implementation(libs.material)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.compiler)
}
