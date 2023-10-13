import com.android.build.api.variant.HasUnitTestBuilder

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "classpath.test"
    compileSdk = 34

    defaultConfig {
        applicationId = "classpath.test"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
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
}

androidComponents {
    beforeVariants(selector().withName("debug")) { variantBuilder ->
        // I'd disable this too, but it makes design tools "run in emulator" feature fail
        // variantBuilder.enableAndroidTest = false
        (variantBuilder as HasUnitTestBuilder).enableUnitTest = false
    }
}

dependencies {
//    implementation("classpath.test:implementation:1.0")
//    runtimeOnly("classpath.test:runtimeOnly:1.0")
//    compileOnly("classpath.test:compileOnly:1.0")

    implementation(project(":implementation"))
    runtimeOnly(project(":runtimeOnly"))
    compileOnly(project(":compileOnly"))



    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.ui.tooling)
}