package org.example.presentation

import org.example.config.dbConnection
import org.example.presentation.form.TodoForm
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.example.service.TodoService
import java.util.Scanner

class TodoApp : KoinComponent {

    private val todoService: TodoService by inject()
    private val dbConnection: dbConnection by inject()
    private val scanner = Scanner(System.`in`)

    fun app() {
        dbConnection.open()
        var appEndFlg = false

        while (!appEndFlg) {
            printMenu()
            when (scanner.next()) {
                "1" -> displayTodoList()
                "2" -> registerTodo()
                "3" -> appEndFlg = true
                else -> println("無効な選択です。もう一度入力してください。")
            }
        }

        dbConnection.close()
    }

    private fun printMenu() {
        println("\n登録している一覧を確認するには「1」、新規登録する場合は「2」、アプリを終了する場合は「3」を入力してください\n")
    }

    private fun displayTodoList() {
        val todos = todoService.getTodoLists()
        println("\n登録しているTODO一覧\n")
        todos.forEach { it.print() }
    }

    private fun registerTodo() {
        println("\n登録したいTODOを入力してください\n")
        val inputTodo = scanner.next()
        val todoForm = TodoForm(inputTodo)

        if (todoForm.valResult) {
            println("再度入力してください")
            return
        }

        println("入力内容に問題なければ「1」を入力してください。登録しない場合は「1」以外を入力してください")
        if (scanner.next() == "1") {
            todoService.registerTodo(todoForm)
        }
        println("機能の選択に戻ります")
    }
}
