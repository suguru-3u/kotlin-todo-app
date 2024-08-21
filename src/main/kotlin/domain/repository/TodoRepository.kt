package org.example.domain.repository

import org.example.domain.model.Todo
import org.example.presentation.form.EditTodoForm
import org.example.presentation.form.TodoForm

interface TodoRepository {
    fun getTodoLists(): MutableList<Todo>
    fun registerTodo(todoForm: TodoForm)
    fun editTodo(editTodoForm: EditTodoForm)
}