plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.6.10"
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {

        val ktorVersion = "2.0.0-beta-1"
        val napierVersion = "2.6.1"

        val commonMain by getting {

            dependencies {
                //KTOR
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")

                //NAPIER
                implementation("io.github.aakira:napier:$napierVersion")

                //SERIALIZATION
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

                //MULTIPLATFORM SETTINGS
                implementation("com.russhwolf:multiplatform-settings:1.0.0-RC")

            }


        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
            }
        }
        val androidUnitTest by getting
    

    }
}


android {
    namespace = "com.vorue.pokedex"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}