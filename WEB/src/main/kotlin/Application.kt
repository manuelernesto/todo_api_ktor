package io.github.manuelernesto.web

import com.github.mustachejava.DefaultMustacheFactory
import io.github.manuelernesto.Service.TodoService
import io.github.manuelernesto.Service.TodoServiceImpl
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.application.log
import io.ktor.features.StatusPages
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.mustache.Mustache
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.dsl.module.module
import org.koin.ktor.ext.inject
import org.koin.standalone.StandAloneContext

val todoAppModule = module {
    single<TodoService> { TodoServiceImpl() }
}

fun main(args: Array<String>) {
    StandAloneContext.startKoin(listOf(todoAppModule))
    embeddedServer(Netty, commandLineEnvironment(args)).start()
}

@Suppress("unused")
fun Application.module() {
    val service: TodoService by inject()
    moduleWithDependency(service)
}

fun Application.moduleWithDependency(service: TodoService) {
    install(StatusPages) {
        when {
            isDev -> {
                this.exception<Throwable> { e ->
                    call.respondText(e.localizedMessage, ContentType.Text.Plain, HttpStatusCode.InternalServerError)
                    throw e
                }
            }
            isTest -> {
                this.exception<Throwable> { e ->
                    call.response.status(HttpStatusCode.InternalServerError)
                    throw e
                }
            }
            isProd -> {
                this.exception<Throwable> { e ->
                    call.respondText(e.localizedMessage, ContentType.Text.Plain, HttpStatusCode.InternalServerError)
                }
            }
        }
    }

    install(Mustache) {
        mustacheFactory = DefaultMustacheFactory("templates")
    }

    install(Routing) {
        if (isDev) trace {
            application.log.trace(it.buildText())
        }

        todos(service)
        staticResources()
    }
}


val Application.envKind
    get() = environment.config.property("ktor.environment").getString()

val Application.isDev get() = envKind == "dev"
val Application.isTest get() = envKind == "test"
val Application.isProd get() = envKind != "dev" && envKind != "test"