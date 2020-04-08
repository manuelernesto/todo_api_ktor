package io.github.manuelernesto

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

fun Routing.todoAPI() {
    route("/api/todo") {
        get("/") {
            call.respond(todos)
        }
    }
}