package io.github.manuelernesto.web.viewmodels

import io.github.manuelernesto.shared.Todo


data class TodoVM(private val todos: List<Todo>) {
    val userName = "Manuel Ernesto"
    val todoItems = todos
}





