import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version System.getProperty("jvmVersion")
}

subprojects {
    group = System.getProperty("group")
    version = System.getProperty("version")
    repositories {
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}