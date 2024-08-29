package org.example

import org.example.config.DBConnection
import org.example.config.KoinConfig.koinPracticModeules
import org.example.presentation.controller.SignController
import org.example.presentation.controller.TodoApp
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.context.GlobalContext.get
import org.koin.core.qualifier.named

fun main() {
    println("Hello Kotlin TODO APP")

    // Koinの初期化
    initializeKoin()

    // サインイン処理
    val signController = SignController()
    while (true) {
        val userInfo = signController.app()
        println("ユーザー情報： $userInfo")
    }

    // TODOアプリケーションの開始
    val todoApp = TodoApp()
    todoApp.app()

    // Koinの停止
    terminateKoin()
    println("アプリを終了します")
}

private fun initializeKoin() {
    startKoin {
        modules(koinPracticModeules)
    }
}

private fun terminateKoin() {
    stopKoin()
}
