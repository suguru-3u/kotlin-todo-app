package org.example.domain.repository

import org.example.presentation.form.EditTodoForm
import org.example.presentation.form.TodoForm
import java.sql.ResultSet

interface TodoRepository {
    fun getTodoLists(): ResultSet?
    fun registerTodo(todoForm: TodoForm)
    fun editTodo(editTodoForm: EditTodoForm)
}