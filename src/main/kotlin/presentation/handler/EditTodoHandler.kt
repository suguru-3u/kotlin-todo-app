package org.example.presentation.handler

import org.example.domain.model.Todo
import org.example.presentation.form.EditTodoForm
import org.example.presentation.utils.confirm
import org.example.presentation.utils.inputDate
import org.example.presentation.utils.inputTodo
import org.example.presentation.utils.inputTodoId
import org.example.service.TodoService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

class EditTodoHandler : KoinComponent {

    private val todoService: TodoService by inject()

    fun editTodo() {
        try {
            val todos = todoService.getTodoLists()
            displayTodos(todos)
            println("\n編集したいTODOのID入力してください\n")
            val inputTodoId = inputTodoId()
            val findTodo = todos.firstOrNull { it.postId == inputTodoId }
            findTodo?.let { edit(it) } ?: println("指定されたIDのTODOが見つかりません")
        } catch (exception: Exception) {
            println("編集に失敗しました： ${exception.message}")
        }
    }

    private fun displayTodos(todos: List<Todo>) {
        println("\n登録しているTODO一覧\n")
        todos.forEach(Todo::print)
    }

    private fun edit(todo: Todo) {
        todo.print()
        if (confirm()) {
            val editTodoForm = createEditTodoForm(todo)
            if (confirm()) {
                updateTodo(editTodoForm)
            } else {
                println("編集がキャンセルされました")
            }
        } else {
            println("編集がキャンセルされました")
        }
    }

    private fun createEditTodoForm(todo: Todo): EditTodoForm {
        val inputTodo = inputTodo()
        val inputDate = inputDate()
        return EditTodoForm(todo.dbId, inputTodo, inputDate)
    }

    private fun updateTodo(editTodoForm: EditTodoForm) {
        runCatching {
            todoService.editTodo(editTodoForm)
        }.onSuccess {
            println("更新が成功しました")
        }.onFailure { exception ->
            println("更新に失敗しました： ${exception.message} 再度試してください")
        }
    }
}