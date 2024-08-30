package org.example.presentation.controller

import org.example.config.DBConnection
import org.example.domain.model.User
import org.example.presentation.handler.SignInHandler
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

class SignController : KoinComponent {

    private val signInHandler: SignInHandler by inject()
    private val scanner = Scanner(System.`in`)

    fun app(): User? {
        var appEndFlg = false
        var userInfo: User? = null
        while (!appEndFlg) {
            println("ログインする場合は「1」、新規登録する場合は「2」を入力してください")

            when (scanner.next()) {
                "1" -> {
                    userInfo = signInHandler.signin()
                    appEndFlg = true
                }

                "2" -> {
                    appEndFlg = true
                }

                else -> println("無効な選択です。もう一度入力してください。")
            }
            println("メニュー選択に戻ります")
        }
        return userInfo
    }
}