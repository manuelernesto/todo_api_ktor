package io.github.manuelernesto.Service

import io.github.manuelernesto.Repository.TodoListRepository
import io.github.manuelernesto.shared.Importance
import io.github.manuelernesto.shared.Todo
import java.time.LocalDate

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

class TodoServiceImpl(val todoListRepository: TodoListRepository) : TodoService {

    override fun getAll() = todos
    override fun getTodo(id: Int) = todos[id]

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