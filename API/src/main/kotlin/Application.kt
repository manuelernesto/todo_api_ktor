package io.github.manuelernesto

import TodoListRepositorySQL
import com.fasterxml.jackson.databind.SerializationFeature
import io.github.manuelernesto.Service.TodoService
import io.github.manuelernesto.Service.TodoServiceImpl
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.jackson.jackson

fun main(args: Array<String>): Unit = io.ktor.server.cio.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.module(testing: Boolean = false) {
    val service = TodoServiceImpl(TodoListRepositorySQL())
    moduleWithDependency(service)
}

fun Application.moduleWithDependency(service: TodoService) {
    install(Routing) {
        todoAPI(service)
    }

    install(StatusPages) {
        this.exception<Throwable> { e ->
            call.respondText(e.localizedMessage, ContentType.Text.Plain)
            throw e
        }
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
}

