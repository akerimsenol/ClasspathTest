import com.android.build.api.variant.HasUnitTestBuilder

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    `maven-publish`
}

android {
    namespace = "classpath.runtimeOnly"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    publishing {
        singleVariant("debug")
    }
}

androidComponents {
    beforeVariants(selector().withName("debug")) { variantBuilder ->
        variantBuilder.enableAndroidTest = false
        (variantBuilder as HasUnitTestBuilder).enableUnitTest = false
    }
}

publishing {
    publications {
        register<MavenPublication>("debug") {
            groupId = "classpath.test"
            artifactId = "runtimeOnly"
            version = "1.0"

            afterEvaluate {
                from(components["debug"])
            }
        }
    }
}