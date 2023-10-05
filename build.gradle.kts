// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply {
        set("lifecycle_version", "2.6.2")
        set("room_version", "2.5.2")
    }
}

plugins {
    id("com.android.application") version "8.0.2" apply false
    id("com.android.library") version "8.0.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.21" apply false
    // The version should be exactly the same, otherwise it causes build error [Don't know why though!]
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}