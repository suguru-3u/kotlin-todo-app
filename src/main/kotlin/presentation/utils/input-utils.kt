package org.example.presentation.utils

import java.time.LocalDate
import java.util.*

val scanner = Scanner(System.`in`)

fun inputTodo(): String {
    println("\n登録・編集したいTODOを入力してください\n")
    return scanner.next()
}

fun inputDate(): LocalDate {
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
                "無効な入力です。半角数字を入力してください", err
            )
        }
    }
}

fun confirm(): Boolean {
    println("入力内容に問題なければ「1」を入力してください。キャンセルする場合は「1」以外を入力してください")
    return scanner.next() == "1"
}

fun inputPrompt(message: String): Int {
    println(message)
    return scanner.next().toInt()
}

fun inputTodoId(): Long {
    return try {
        scanner.next().toLong()
    } catch (e: NumberFormatException) {
        throw NumberFormatException("無効なIDです。半角数字を入力してください")
    }
}