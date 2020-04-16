package io.github.manuelernesto.web


import io.github.manuelernesto.shared.Importance
import io.github.manuelernesto.shared.Todo
import io.github.manuelernesto.web.viewmodels.TodoVM
import io.ktor.application.call
import io.ktor.mustache.MustacheContent
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import java.time.LocalDate

val todo = Todo(
    1,
    "Add database processing",
    "Add backend support to this code",
    "Ernesto",
    LocalDate.of(2020, 4, 18),
    Importance.HIGH
)

var todos = listOf(todo, todo)

fun Routing.todos() {
    get("/todo") {

        val vm = TodoVM(todos)

        call.respond(
            MustacheContent("todos.hbs", mapOf("todos" to vm))
        )
    }
}