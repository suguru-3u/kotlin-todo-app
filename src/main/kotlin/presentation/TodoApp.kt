package org.example.presentation

import org.example.presentation.form.TodoForm
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.example.service.TodoService
import java.util.*

class TodoApp : KoinComponent {
    private val todoService: TodoService by inject()

    fun app() {

        var appEndFlg = false
        while (!appEndFlg) {
            println("")
            println("登録している一覧を確認するには、「１」、新規登録する場合は「２」、アプリを終了する場合は「３」を入力してください")
            println("")

            val scan = Scanner(System.`in`)
            val selectCommand = scan.next()
            if (selectCommand.equals("1")) {
                val todos =
                    todoService.getTodoLists()
                println("")
                println("登録しているTODO一覧")
                println("")
                todos.forEach{ it.print()}
            }
            if(selectCommand.equals("2")){
                println("")
                println("登録したいTODOを入力してください")
                println("")
                val inputTodo =  Scanner(System.`in`).next()
                val todoFrom = TodoForm(inputTodo)
                if(todoFrom.validationResult){
                    println("再度入力してください")
                }
            }
            // DBと接続するクラスを作成
            // アプリ起動時にDBと接続するようにする

            if (selectCommand.equals("3")) {
                appEndFlg = true
            }
        }
    }
}