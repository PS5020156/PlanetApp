plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = Configs.namespace
    compileSdk = Configs.compileSdk

    defaultConfig {
        applicationId = Configs.applicationId
        minSdk = Configs.minSdk
        targetSdk = Configs.targetSdk
        versionCode = Configs.versionCode
        versionName = Configs.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

}

dependencies {

    implementation(Dependencies.core)
    implementation(Dependencies.lifecycleRuntime)
    implementation(Dependencies.activityCompose)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.toolingPreview)
    implementation(Dependencies.material3)
    debugImplementation(Dependencies.uiTooling)
    debugImplementation(Dependencies.testManifest)
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltAndroidCompiler)
    implementation(Dependencies.gson)
    implementation(Dependencies.retrofit2)
    implementation(Dependencies.runtimeCompose)
    implementation(Dependencies.navigationCompose)
    implementation(Dependencies.navigationUi)
    implementation(Dependencies.fragmentKtx)
    implementation(Dependencies.room)
    annotationProcessor(Dependencies.roomCompiler)
    kapt(Dependencies.roomCompiler)
    implementation(Dependencies.roomKtx)
    implementation(Dependencies.navigationComposeHilt)

    testImplementation(Dependencies.roomTesting)
    testImplementation(Dependencies.mockServer)
    testImplementation(Dependencies.coroutineTest)
    testImplementation(Dependencies.mockitoTest)
    testImplementation(Dependencies.mockitoKotlinTest)
    testImplementation(Dependencies.coreTesting)
    testImplementation(Dependencies.turbine)
    testImplementation(Dependencies.mockitoInline)
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.extJunit)
    androidTestImplementation(Dependencies.espresso)
    androidTestImplementation(platform(Dependencies.composeBomTest))
    androidTestImplementation(Dependencies.junit4)
}