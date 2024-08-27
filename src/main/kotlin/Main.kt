package org.example

import org.example.config.KoinConfig.koinPracticModeules
import org.example.presentation.controller.TodoApp
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

fun main() {
    println("Hello Kotlin TODO APP")

    // アプリケーション開始時にKoinを起動
    startKoin {
        modules(koinPracticModeules)
    }

    val todoApp = TodoApp()
    todoApp.app()

    // アプリケーション終了時にKoinを停止
    stopKoin()
    println("アプリを終了します")
}

