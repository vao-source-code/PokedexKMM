
plugins {
    id("com.android.application")
    kotlin("android")
    id("com.squareup.sqldelight")

}

android {
    namespace = "com.vorue.pokedex.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.vorue.pokedex.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}




dependencies {

    val retrofit_version = "2.6.2"
    val coroutines_version = "1.3.9"
    val lifecycle_version = "2.5.1"
    val dagger_version = "2.38.1"
    val ktor_version = "2.0.0-beta-1"

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")


    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.4.0")
    implementation("androidx.compose.ui:ui-tooling:1.4.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.0")
    implementation("androidx.compose.foundation:foundation:1.4.0")
    implementation("androidx.compose.material:material:1.4.0")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")



    // Retrofit
    implementation ("com.squareup.retrofit2:converter-gson:$retrofit_version")
    implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")


    // ViewModel
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")


    //Dagger hint
    implementation ("com.google.dagger:hilt-android:$dagger_version")

    // Fragment
    implementation ("androidx.fragment:fragment-ktx:1.4.0")

    //logging client
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.1")

    implementation ("com.github.bumptech.glide:glide:4.13.0")

    //KTOR
    implementation("io.ktor:ktor-client-core:$ktor_version")

    //Normalize
    implementation ("com.intuit.ssp:ssp-android:1.1.0")
    implementation ("com.intuit.sdp:sdp-android:1.1.0")

    //SQLLight
    implementation("com.squareup.sqldelight:android-driver:1.5.5")



}