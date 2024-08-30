package org.example.presentation.utils

import java.time.LocalDate

fun inputTodo(): String {
    println("\n登録・編集したいTODOを入力してください\n")
    return readln()
}

fun inputDate(): LocalDate {
    println("完了日を入力してください")
    println("本日完了予定の場合「1」を入力してください。「1」以外の場合、完了予定日の入力を行います")

    return if (readln() == "1") {
        LocalDate.now()
    } else {
        val year = inputPrompt("「年」を入力してください")
        val month = inputPrompt("「月」を入力してください")
        val day = inputPrompt("「日」を入力してください")
        LocalDate.of(year, month, day)
    }
}

fun confirm(): Boolean {
    println("入力内容に問題なければ「1」を入力してください。キャンセルする場合は「1」以外を入力してください")
    return readln() == "1"
}

fun inputPrompt(message: String): Int {
    println(message)
    return readln().toIntOrNull()
        ?: throw IllegalArgumentException("無効な入力です。半角数半角数字を入力してください")
}

fun inputTodoId(): Long {
    return readln().toLongOrNull()
        ?: throw NumberFormatException("無効なIDです。半角数字を入力してください")
}