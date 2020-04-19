package io.github.manuelernesto

import io.github.manuelernesto.DataAcess.TodoService
import io.github.manuelernesto.shared.Todo
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import java.lang.IllegalArgumentException

fun Routing.todoAPI(service: TodoService) {
    route("/api") {

        header("Accept", "application/json") {
            get("/todo") {
                val todos = service.getAll()
                call.respond(todos)
            }
        }

        get("/todo/{id}") {
            val id: String = call.parameters["id"]!!
            try {
                val todo = service.getTodo(id.toInt())
                call.respond(todo)
            } catch (e: Throwable) {
                call.respond(HttpStatusCode.NotFound)
            }
        }
        post("/todo") {
            val todo = call.receive<Todo>()
            service.create(todo)
            call.respond(HttpStatusCode.Created)
        }

        put("/todo/{id}") {
            val id = call.parameters["id"] ?: throw IllegalArgumentException("Missing id")
            val todo = call.receive<Todo>()
            service.update(id.toInt(), todo)
            call.respond(HttpStatusCode.NoContent)
        }

        delete("/todo/{id}") {
            val id = call.parameters["id"] ?: throw IllegalArgumentException("Missing id")
            service.delete(id.toInt())
            call.respond(HttpStatusCode.NoContent)
        }
    }
}

