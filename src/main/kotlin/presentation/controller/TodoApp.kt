package org.example.presentation.controller

import org.example.config.dbConnection
import org.example.domain.model.Todo
import org.example.presentation.form.DeleteTodoForm
import org.example.presentation.form.EditTodoForm
import org.example.presentation.form.TodoForm
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.example.service.TodoService
import java.time.LocalDate
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

    // TODO:完了日時が何件すぎているのか件数が出るように修正を実施
    private fun displayTodoList() {
        val todos = todoService.getTodoLists()
        println("\n登録しているTODO一覧\n")
        if (todos.isEmpty()) {
            println("登録されているTODOはありません")
        } else {
            todos.forEach(Todo::print)
        }
    }

    private fun inputTodo(): String {
        println("\n登録・編集したいTODOを入力してください\n")
        return scanner.next()
    }

    private fun inputDate(): LocalDate {
        println("完了日を入力してください")
        println("本日完了予定の場合「1」を入力してください。「1」以外の場合、完了予定日の入力を行います")

        return if (scanner.next() == "1") {
            LocalDate.now()
        } else {
            runCatching {
                val year = inputPrompt("「年」を入力してください")
                val month = inputPrompt("「月」を入力してください")
                val day = inputPrompt("「日」を入力してください")
                LocalDate.of(year, month, day)
            }.getOrElse { err ->
                throw IllegalArgumentException(
                    "無効な入力です。半角数字を入力してください",
                    err
                )
            }
        }
    }

    private fun inputPrompt(message: String): Int {
        println(message)
        return scanner.next().toInt()
    }

    private fun registerTodo() {
        try {
            val inputTodo = inputTodo()
            val inputDate = inputDate()
            val todoForm = TodoForm(inputTodo, inputDate)

            if (todoForm.valResult) {
                println("入力に問題があります。再度入力してください")
                return
            }

            println("入力内容に問題なければ「1」を入力してください。登録しない場合は「1」以外を入力してください")
            if (scanner.next() == "1") {
                todoService.registerTodo(todoForm)
                println("TODOが登録されました")
            } else {
                println("TODOの登録をキャンセルしました")
            }

        } catch (err: Exception) {
            println(err.message)
            println("登録に失敗しました。再度試してください")
        } finally {
            println("メニューに戻ります")
        }
    }

    private fun editTodo() {
        try {
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
                findTodo.print()
                println("選択内容に問題なければ「1」を入力してください。編集しない場合は「1」以外を入力してください")
                if (scanner.next() == "1") {
                    val inputTodo = inputTodo()
                    val inputDate = inputDate()
                    val editTodoForm = EditTodoForm(findTodo.dbId, inputTodo,inputDate)

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
        } catch (err: Exception) {
            println(err.message)
            println("編集に失敗しました。再度試してください")
        } finally {
            println("メニューに戻ります")
        }
    }

    private fun deleteTodo() {
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
