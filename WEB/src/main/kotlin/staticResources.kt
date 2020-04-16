package io.github.manuelernesto.web

import io.ktor.http.content.default
import io.ktor.http.content.files
import io.ktor.http.content.static
import io.ktor.http.content.staticRootFolder
import io.ktor.routing.Routing
import java.io.File

fun Routing.staticResources() {
    static {
        staticRootFolder = File("wwwroot")

        static("css") {
            files("css")
        }

        static("js") {
            files("js")
        }

        default("index.html")
    }
}