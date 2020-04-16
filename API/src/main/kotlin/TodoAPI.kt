package io.github.manuelernesto

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import java.time.LocalDate

fun Routing.todoAPI() {
    route("/api") {

        header("Accept", "application/json") {
            get("/todo") {
                call.respond(todos)
            }
        }

        get("/todo/{id}") {
            val id: String = call.parameters["id"]!!
            try {
                val todo = todos[id.toInt()]
                call.respond(todo)
            } catch (e: Throwable) {
                call.respond(HttpStatusCode.NotFound)
            }
        }
        post("/todo") {
            val todo = call.receive<Todo>()
            val newTodo = Todo(
                todos.size + 1, todo.title, todo.details, todo.assignedTo, todo.dueData, todo.importance
            )
            todos = todos + todo
            call.respond(HttpStatusCode.Created, todos)
        }

        put("/todo/{id}") {
            val id: String? = call.parameters["id"]

            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@put
            }

            val todoFound = todos.getOrNull(id.toInt())

            if (todoFound == null) {
                call.respond(HttpStatusCode.NotFound)
                return@put
            }

            val todo = call.receive<Todo>()
            todos = todos.filter { it.id != todo.id }
            todos = todos + todo

            call.respond(HttpStatusCode.NoContent)
        }

        delete("/todo/{id}") {
            val id: String? = call.parameters["id"]

            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@delete
            }

            val todoFound = todos.getOrNull(id.toInt())

            if (todoFound == null) {
                call.respond(HttpStatusCode.NotFound)
                return@delete
            }

            val todo = call.receive<Todo>()
            todos = todos.filter { it.id != todo.id }

            call.respond(HttpStatusCode.NoContent)
        }
    }
}

val todo1 = Todo(
    1,
    "Learn Kotlin Today",
    "Learn more about The best language",
    "Manuel Ernesto",
    LocalDate.of(2020, 4, 8),
    Importance.HIGH
)
val todo2 = Todo(
    2,
    "Learn Python",
    "Another great language",
    "Manuel Ernesto",
    LocalDate.of(2020, 4, 9),
    Importance.MEDIUM
)

var todos = listOf(todo1, todo2)