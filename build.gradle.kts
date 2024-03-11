import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    val agp_version by extra("7.4.0")
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.4.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    kotlin("jvm") version "1.9.0"
    id("com.google.dagger.hilt.android") version "2.48" apply false
}
dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "11"
}