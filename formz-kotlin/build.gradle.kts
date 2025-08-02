import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.dokka)
    alias(libs.plugins.vanniktech.mavenPublish)
}

group = "io.github.zainulhassan815"
version = "1.0.1"

kotlin {
    explicitApi()

    jvm()
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    linuxX64()

    sourceSets {
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

android {
    namespace = "org.dreamerslab.formz"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

tasks.dokkaHtml {
    outputDirectory.set(rootProject.layout.projectDirectory.dir("docs"))
}

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()
    coordinates(group.toString(), "formz", version.toString())

    pom {
        name = "Formz Kotlin"
        description = "A Multiplatform Form Validation Library"
        url = "https://github.com/zainulhassan815/formz-kotlin"

        licenses {
            license {
                name = "Apache License"
                url = "https://opensource.org/license/apache-2-0"
            }
        }
        developers {
            developer {
                id = "zainulhassan815"
                name = "Zain Ul Hassan"
                url = "https://github.com/zainulhassan815"
                email = "zainulhassan4330@gmail.com"
            }
        }
        scm {
            url = "https://github.com/zainulhassan815/formz-kotlin"
        }
    }
}
