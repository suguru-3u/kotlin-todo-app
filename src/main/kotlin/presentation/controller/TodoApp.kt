package org.example.presentation.controller

import org.example.config.DBConnection
import org.example.presentation.handler.DisplayTodoHandler
import org.example.presentation.handler.EditTodoHandler
import org.example.presentation.handler.RegisterTodoHandler
import org.example.presentation.handler.DeleteTodoHandler
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.Scanner

class TodoApp : KoinComponent {

    private val dbConnection: DBConnection by inject()
    private val scanner = Scanner(System.`in`)

    fun app() {
        dbConnection.open()
        var appEndFlg = false
        val displayTodoHandler = DisplayTodoHandler()
        val registerTodoHandler = RegisterTodoHandler()
        val editTodoHandler = EditTodoHandler()
        val deleteTodoHandler = DeleteTodoHandler()

        while (!appEndFlg) {
            printMenu()
            when (scanner.next()) {
                "1" -> displayTodoHandler.displayTodos()
                "2" -> registerTodoHandler.registerTodo()
                "3" -> editTodoHandler.editTodo()
                "4" -> deleteTodoHandler.deleteTodo()
                "5" -> appEndFlg = true
                else -> println("無効な選択です。もう一度入力してください。")
            }
            println("メニュー選択に戻ります")
        }
        dbConnection.close()
    }

    private fun printMenu() {
        println(
            """
            | 
            | 登録している一覧を確認するには「1」、
            | 新規登録する場合は「2」、
            | 編集する場合は「3」、
            | 削除する場合は「4」、
            | アプリを終了する場合は「5」を入力してください
            |
            """.trimMargin()
        )
    }
}
