package io.github.manuelernesto.DataAcess

import io.github.manuelernesto.Repository.TodoListRepository
import io.github.manuelernesto.shared.Importance
import io.github.manuelernesto.shared.Todo

import java.time.LocalDate

val todo1 = Todo(
    1,
    "Add database processing 1",
    "Add backend support to this code",
    "Kevin",
    LocalDate.of(2020, 4, 17),
    Importance.HIGH
)

val todo2 = Todo(
    2,
    "Add rest processing",
    "Add better support to this code",
    "Kevin",
    LocalDate.of(2020, 4, 18),
    Importance.HIGH
)

val todos = listOf(todo1, todo2)

class TodoServiceImpl(val repository: TodoListRepository) : TodoService {
    override fun getAll(): List<Todo> = todos

    override fun getTodo(id: Int): Todo = todos[id]

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