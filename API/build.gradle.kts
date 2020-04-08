val logback_version: String by project
val ktor_version: String by project
val kotlin_version: String by project

plugins {
    application
}

application {
    mainClassName = "io.ktor.server.cio.EngineMain"
}

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
    implementation("io.ktor:ktor-jackson:$ktor_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
}
