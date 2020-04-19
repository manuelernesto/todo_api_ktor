package io.github.manuelernesto.Service

import io.github.manuelernesto.shared.Todo
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JacksonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get

class TodoServiceImpl : TodoService {

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = JacksonSerializer()
        }
    }
    private val endpoint = "http://0.0.0.0:8081/api/todo"

    override suspend fun getAll(): List<Todo> = client.get(endpoint)

    override fun getTodo(id: Int): Todo {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun create(todo: Todo): Boolean {
        TODO("Not yet implemented")
    }

    override fun update(id: Int, todo: Todo): Boolean {
        TODO("Not yet implemented")
    }

}