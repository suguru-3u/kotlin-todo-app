package org.example.service

import org.example.domain.model.Todo
import org.example.domain.repository.TodoRepository
import org.example.presentation.form.TodoForm
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TodoService: KoinComponent {
    private val todoRepository: TodoRepository by inject()

    fun getTodoLists(): MutableList<Todo> {
        return todoRepository.getTodoLists()
    }

    fun registerTodo(todoForm: TodoForm){
        todoRepository.registerTodo(todoForm)
    }
}