package io.github.manuelernesto

import TodoListRepositorySQL
import com.fasterxml.jackson.databind.SerializationFeature
import io.github.manuelernesto.DataAcess.TodoService
import io.github.manuelernesto.DataAcess.TodoServiceImpl
import io.github.manuelernesto.Repository.TodoListRepository
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.jackson.jackson
import org.koin.dsl.module.module
import org.koin.ktor.ext.inject
import org.koin.standalone.StandAloneContext

val todoAppModule = module {
    single<TodoService> { TodoServiceImpl(get()) }
    single<TodoListRepository> { TodoListRepositorySQL() }
}

fun main(args: Array<String>) {
    StandAloneContext.startKoin(listOf(todoAppModule))
    io.ktor.server.cio.EngineMain.main(args)
}

@Suppress("unused") // Referenced in application.conf
fun Application.module(testing: Boolean = false) {
    val service: TodoService by inject()
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

