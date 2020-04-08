val logback_version: String by project
val kotlin_version: String by project
val ktor_version: String by project
val spek_version: String by project
val jackson_version: String by project
val kluent_version: String by project

buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.70")
    }
}


plugins {
    java
}

allprojects {

    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "kotlin")
    apply(plugin = "application")
    group = "io.github.manuelernesto"
    version = "0.0.1"

    repositories {
        mavenLocal()
        jcenter()
        maven { url = uri("https://kotlin.bintray.com/ktor") }
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
        implementation("io.ktor:ktor-server-cio:$ktor_version")
        implementation("ch.qos.logback:logback-classic:$logback_version")
        implementation("io.ktor:ktor-server-core:$ktor_version")
        implementation("io.ktor:ktor-server-host-common:$ktor_version")
        testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    }

    tasks.withType<Test> {
        useJUnitPlatform {
            includeEngines("spek2")
        }
    }

}
subprojects {
    version = "1.0"
}

project(":API") {
    dependencies {
        implementation(project(":Shared"))
    }
}