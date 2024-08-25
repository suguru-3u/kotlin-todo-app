package org.example.presentation

import org.example.config.dbConnection
import org.example.domain.model.Todo
import org.example.presentation.form.DeleteTodoForm
import org.example.presentation.form.EditTodoForm
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
                "3" -> editTodo()
                "4" -> deleteTodo()
                "5" -> appEndFlg = true
                else -> println("無効な選択です。もう一度入力してください。")
            }
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

    private fun displayTodoList() {
        val todos = todoService.getTodoLists()
        println("\n登録しているTODO一覧\n")
        if(todos.isEmpty()){
            println("登録されているTODOはありません")
        }else{
            todos.forEach(Todo::print)
        }
    }

    private fun inputTodo(): String {
        println("\n登録・編集したいTODOを入力してください\n")
        return scanner.next()
    }

    private fun registerTodo() {
        val inputTodo = inputTodo()
        val todoForm = TodoForm(inputTodo)

        if (todoForm.valResult) {
            println("再度入力してください")
            return
        }

        println("入力内容に問題なければ「1」を入力してください。登録しない場合は「1」以外を入力してください")
        if (scanner.next() == "1") {
            todoService.registerTodo(todoForm)
            println("TODOが登録されました")
        } else {
            println("TODOの登録をキャンセルしました")
        }

        println("メニューに戻ります")
    }

    private fun editTodo() {
        val todos = todoService.getTodoLists()
        println("\n登録しているTODO一覧\n")
        todos.forEach { it.print() }

        println("\n編集したいTODOのID入力してください\n")
        val inputTodoId = try {
            scanner.next().toLong()
        } catch (e: NumberFormatException) {
            println("無効なIDです。半角数字を入力してください")
            return
        }

        val findTodo = todos.firstOrNull { it.postId == inputTodoId }
        findTodo?.let {
            println("選択したID： ${findTodo.postId}")
            println("選択したTODO： ${findTodo.title}")
            println("選択内容に問題なければ「1」を入力してください。編集しない場合は「1」以外を入力してください")
            if (scanner.next() == "1") {
                val inputTodo = inputTodo()
                val editTodoForm = EditTodoForm(findTodo.dbId, inputTodo)

                if (editTodoForm.valResult) {
                    println("再度入力してください")
                    return
                }

                println("入力内容に問題なければ「1」を入力してください。編集しない場合は「1」以外を入力してください")
                if (scanner.next() == "1") {
                    todoService.editTodo(editTodoForm)
                    println("更新が成功しました")
                }
            } else {
                println("編集がキャンセルされました")
            }
        } ?: println("指定されたIDのTODOが見つかりません")

        println("メニューに戻ります")
    }

    private fun deleteTodo(){
        val todos = todoService.getTodoLists()
        println("\n登録しているTODO一覧\n")
        todos.forEach { it.print() }

        println("\n削除したいTODOのID入力してください\n")
        val inputTodoId = try {
            scanner.next().toLong()
        } catch (e: NumberFormatException) {
            println("無効なIDです。半角数字を入力してください")
            return
        }

        val findTodo = todos.firstOrNull { it.postId == inputTodoId }
        findTodo?.let {
            println("選択したID： ${findTodo.postId}")
            println("選択したTODO： ${findTodo.title}")
            println("選択内容に問題なければ「1」を入力してください。削除しない場合は「1」以外を入力してください")
            if (scanner.next() == "1") {
                val deleteTodoForm = DeleteTodoForm(findTodo.dbId)
                    todoService.deleteTodo(deleteTodoForm)
                    println("削除が成功しました")

            } else {
                println("削除がキャンセルされました")
            }
        } ?: println("指定されたIDのTODOが見つかりません")

        println("メニューに戻ります")
    }
}
