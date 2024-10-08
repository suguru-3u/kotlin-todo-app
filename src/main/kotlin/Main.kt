package org.example

import org.example.config.DBConnection
import org.example.config.KoinConfig.koinPracticModeules
import org.example.domain.model.User
import org.example.domain.model.UserId
import org.example.presentation.controller.SignController
import org.example.presentation.controller.TodoController
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

fun main() {
    println("Hello Kotlin TODO APP")

    // Koinの初期化
    initializeKoin()

    val appComponents = AppComponents()
    appComponents.dbConnection.open()

    // サインイン処理
    val signController = SignController()
    var userInfo: UserId?
    var nonLoginFlg = true
    while (nonLoginFlg) {
        userInfo = signController.app()
        if(userInfo != null) {
            println(userInfo)
            nonLoginFlg = false
        }
    }

    // TODOアプリケーションの開始
    val todoController = TodoController()
    todoController.app()

    // Koinの停止
    terminateKoin()
    appComponents.dbConnection.close()
    println("アプリを終了します")
}

fun initializeKoin() {
    startKoin {
        modules(koinPracticModeules)
    }
}

fun terminateKoin() {
    stopKoin()
}

// KoinComponentを実装するクラスを作成
class AppComponents : KoinComponent {
    val dbConnection: DBConnection by inject()
}
