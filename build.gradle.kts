import com.sun.org.apache.regexp.internal.RETest.test

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

//        implementation("ch.qos.logback:logback-classic:$logback_version")

        //jackson
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jackson_version")
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jackson_version")
//        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")

//        testImplementation("org.amshove.kluent:kluent:$kluent_version")
//        testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spek_version")
//
//        testRuntime("org.spekframework.spek2:spek-runner-junit5:$spek_version")

        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
        implementation("io.ktor:ktor-server-cio:$ktor_version")
        implementation("ch.qos.logback:logback-classic:$logback_version")
        implementation("io.ktor:ktor-server-core:$ktor_version")
        implementation("io.ktor:ktor-server-host-common:$ktor_version")
        implementation("io.ktor:ktor-jackson:$ktor_version")


        testImplementation("org.amshove.kluent:kluent:$kluent_version")
        testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spek_version")
        testRuntime("org.spekframework.spek2:spek-runner-junit5:$spek_version")
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

project(":Shared") {
}

project(":API") {
    dependencies {
        implementation(project(":Shared"))
    }
}

project(":WEB") {
    dependencies {
        implementation(project(":Shared"))
    }
}