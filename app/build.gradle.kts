plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.farmerassist"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.farmerassist"
        minSdk = 26
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

    buildFeatures{
        viewBinding = true;
    }
}

dependencies {


    implementation (libs.appcompat.v151)
    //implementation (libs.material.v160)
    implementation(libs.material)
    implementation (libs.constraintlayout.v211)
    implementation (libs.gridlayout)
    implementation(libs.activity)
    testImplementation (libs.junit)
    //androidTestImplementation (libs.junit.v113)
    androidTestImplementation (libs.espresso.core.v340)

    // retrofit for api

    implementation (libs.retrofit)
    implementation (libs.converter.gson)  // For JSON conversion
    implementation (libs.okhttp) // For OkHttp
    implementation (libs.logging.interceptor) // Logging Interceptor
    //Glide load url to image
    implementation (libs.glide)
    annotationProcessor (libs.compiler)
    implementation ("com.google.android.gms:play-services-location:21.3.0")

}