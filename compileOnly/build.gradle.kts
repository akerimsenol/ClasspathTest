import com.android.build.api.variant.HasUnitTestBuilder

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "classpath.compileonly"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

androidComponents {
    beforeVariants(selector().withName("debug")) { variantBuilder ->
        variantBuilder.enableAndroidTest = false
        (variantBuilder as HasUnitTestBuilder).enableUnitTest = false
    }
}
