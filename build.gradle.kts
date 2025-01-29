plugins {
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.dokka).apply(false)
    alias(libs.plugins.vanniktech.mavenPublish) apply false
}
