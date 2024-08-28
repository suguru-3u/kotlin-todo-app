package org.example.presentation.handler

import org.example.presentation.form.TodoForm
import org.example.presentation.utils.confirm
import org.example.presentation.utils.inputDate
import org.example.presentation.utils.inputTodo
import org.example.service.TodoService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RegisterTodoHandler : KoinComponent {

    private val todoService: TodoService by inject()

    fun registerTodo() {
        val todoText = inputTodo()
        val dueDate = inputDate()
        val todoForm = TodoForm(todoText, dueDate)

        if (confirm()) {
            register(todoForm)
        } else {
            println("TODOの登録をキャンセルしました")
        }
    }

    private fun register(todoForm: TodoForm) {
        runCatching {
            todoService.registerTodo(todoForm)
        }.onSuccess {
            println("TODOが登録されました")
        }.onFailure { exception ->
            println("登録に失敗しました： ${exception.message} 再度試してください")
        }
    }
}