package org.example.presentation.form

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class TodoForm(val title: String,val inputDate: LocalDate, var valResult: Boolean = title.isBlank()) {
    init {
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        println("\n入力した内容: $title\n")
        println("\n入力した内容: ${inputDate.format(formatter)}\n")
        if (valResult) println("空文字を検知しました")
    }

    // 日付のチェック
    // 西暦かどうか
    // 月と日付の確認
    // 時間の確認
}
