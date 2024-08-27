package org.example.presentation.form

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class TodoForm(
    val title: String,
    val inputDate: LocalDate,
    var valResult: Boolean = title.isBlank()
) {
    init {
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        println("入力した内容: ${title}")
        println("入力した内容: ${inputDate.format(formatter)}")
        if (valResult) println("空文字を検知しました")
    }
}
