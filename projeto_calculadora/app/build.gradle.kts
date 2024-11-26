plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.projeto_calculadora"
    compileSdk = 34

    testOptions{
        unitTests.isIncludeAndroidResources = true
    }
    defaultConfig {
        applicationId = "com.example.projeto_calculadora"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    testImplementation(libs.junit)
    //testImplementation(libs.mockito.core)
    androidTestImplementation(libs.ext.junit)


    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation("androidx.test.espresso:espresso-idling-resource:3.5.1") // Adicionando a versão corretamente

    //androidTestImplementation(libs.mockito.android)

    androidTestImplementation(libs.test.rules)








}