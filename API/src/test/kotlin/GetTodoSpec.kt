package io.github.manuelernesto

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.config.MapApplicationConfig
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.createTestEnvironment
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import org.amshove.kluent.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.time.LocalDate


object GetTodoSpec : Spek({

    val todo = Todo(
        1,
        "Add database processing",
        "Add backend support to this code",
        "Kevin",
        LocalDate.of(2018, 12, 18),
        Importance.HIGH
    )

    describe("Get the todos") {
        val engine = TestApplicationEngine(createTestEnvironment())
        engine.start(wait = false)

        with(engine) {
            (environment.config as MapApplicationConfig).apply {
                put("ktor.environment", "test")
            }
        }

        engine.application.module(true)
        val mapper = jacksonObjectMapper()
            .registerModule(JavaTimeModule())

        with(engine) {

            it("should create a todo") {
                with(handleRequest(HttpMethod.Post, "/api/todo") {
                    addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                    setBody(mapper.writeValueAsString(todo))
                }) {
                    response.status().`should be`(HttpStatusCode.Created)
                }
            }

            it("Should be ok to get the list of the TODOS") {
                handleRequest(HttpMethod.Get, "/api/todo/0").apply {
                    response.status().`should be`(HttpStatusCode.OK)
                }

            }

            it("should get the todos") {
                with(handleRequest(HttpMethod.Get, "/api/todo/1")) {
                    response
                        .content
                        .shouldNotBeNull()
                        .shouldContain("Python")
                }
            }


            it("should update the todos") {
                with(handleRequest(HttpMethod.Put, "/api/todo/1") {
                    addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                    setBody(mapper.writeValueAsString(todo))
                }) {
                    response.status().`should be`(HttpStatusCode.NoContent)
                }
            }

            it("should delete the todos") {
                with(handleRequest(HttpMethod.Delete, "/api/todo/1")) {
                    response.status().`should be`(HttpStatusCode.NoContent)
                }
            }

            it("should get the todo if the id is set") {

                with(handleRequest(HttpMethod.Get, "/api/todo/1")) {
                    response.content
                        .shouldNotBeNull()
                        .shouldContain("database")
                }
            }

            it("should return an error if the id is invalid") {

                with(handleRequest(HttpMethod.Get, "/api/todo/3")) {
                    response.status().shouldEqual(HttpStatusCode.NotFound)
                }
            }
        }
    }
})