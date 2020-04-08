package io.github.manuelernesto

import io.ktor.application.call
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import java.time.LocalDate

fun Routing.todoAPI() {
    route("/api") {

        get("/todo") {
            call.respond(todos)
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
                todos.size + 1,
                todo.title,
                todo.details,
                todo.assignedTo,
                todo.dueData,
                todo.importance
            )
            todos = todos + todo

            call.respond(HttpStatusCode.Created, todos)
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