package org.example.presentation.form

import com.mysql.cj.xdevapi.Schema.Validation

data class TodoForm(val title: String, var valResult: Boolean = title.isBlank()) {
    init {
        println("\n入力した内容: $title\n")
        if (valResult) println("空文字を検知しました")
    }
}
