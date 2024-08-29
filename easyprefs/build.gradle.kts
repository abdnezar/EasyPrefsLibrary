plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.abdnezar"
            artifactId = "EasyPrefsLibrary"
            version = "1.0.1"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}

android {
    namespace = "com.abdnezar.easyprefs"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
    }

    buildTypes {
        release {
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
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.gson)
}