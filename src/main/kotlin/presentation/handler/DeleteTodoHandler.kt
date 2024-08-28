package org.example.presentation.handler

import org.example.domain.model.Todo
import org.example.presentation.form.DeleteTodoForm
import org.example.presentation.utils.confirm
import org.example.presentation.utils.inputTodoId
import org.example.service.TodoService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

class DeleteTodoHandler : KoinComponent {

    private val todoService: TodoService by inject()

    fun deleteTodo() {
        val todos = runCatching {
            todoService.getTodoLists()
        }.onFailure { exception ->
            println("TODOリストの取得に失敗しました： ${exception.message} 再度試してください")
            return
        }.getOrNull() ?: return // リストの取得に失敗した場合は処理を終了

        displayTodos(todos)

        val inputTodoId = runCatching { inputTodoId() }
            .onFailure { exception ->
                println("TODOのID入力に失敗しました： ${exception.message} 再度試してください")
                return
            }.getOrNull() ?: return // ID入力に失敗した場合は処理を終了

        val todoToDelete = todos.find { it.postId == inputTodoId }

        todoToDelete?.let { processDeletion(it) }
            ?: println("指定されたIDのTODOが見つかりません")
    }

    private fun displayTodos(todos: List<Todo>) {
        println("\n登録しているTODO一覧\n")
        todos.forEach(Todo::print)
        println("\n削除したいTODOのIDを入力してください\n")
    }

    private fun processDeletion(todo: Todo) {
        todo.print()
        if (confirm()) {
            val deleteTodoForm = DeleteTodoForm(todo.dbId)
            performDeletion(deleteTodoForm)
        } else {
            println("削除がキャンセルされました")
        }
    }

    private fun performDeletion(deleteTodoForm: DeleteTodoForm) {
        runCatching {
            todoService.deleteTodo(deleteTodoForm)
        }.onSuccess {
            println("削除が成功しました")
        }.onFailure { exception ->
            println("削除に失敗しました： ${exception.message} 再度試してください")
        }
    }
}