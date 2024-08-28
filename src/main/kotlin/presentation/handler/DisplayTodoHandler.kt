package org.example.presentation.handler

import org.example.domain.model.Todo
import org.example.service.TodoService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDate

class DisplayTodoHandler : KoinComponent {

    private val todoService: TodoService by inject()

    fun displayTodos() {
        runCatching {
            todoService.getTodoLists()
        }.onSuccess { todos ->
            println("\n-----登録しているTODO一覧-----\n")
            if (todos.isEmpty()) {
                println("登録されているTODOはありません")
            } else {
                displaySummary(todos)
                todos.forEach(Todo::print)
            }
        }.onFailure { err ->
            println("一覧取得に失敗しました: ${err.message}。再度試してください")
        }
    }

    private fun displaySummary(todos: List<Todo>) {
        val today = LocalDate.now()
        println("登録しているTodoの数: ${todos.size}")
        println("本日完了予定日のTodoの数: ${todos.count { it.finishDate == today }}")
        println("完了予定日がすぎているTodoの数: ${todos.count { it.finishDate < today }} \n")
    }
}