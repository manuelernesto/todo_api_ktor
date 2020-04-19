package io.github.manuelernesto.DataAcess

import io.github.manuelernesto.shared.Todo

interface TodoService {
    fun getAll(): List<Todo>
    fun getTodo(id: Int): Todo
    fun delete(id: Int): Boolean
    fun create(todo: Todo): Boolean
    fun update(id: Int, todo: Todo): Boolean
}