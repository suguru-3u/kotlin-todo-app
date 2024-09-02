package org.example.presentation.controller

import org.example.domain.model.User
import org.example.domain.model.UserId
import org.example.presentation.handler.SignInHandler
import org.example.presentation.handler.SignUpHandler
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignController : KoinComponent {

    private val signInHandler: SignInHandler by inject()
    private val signUpHandler: SignUpHandler by inject()

    fun app(): UserId? {
        var nonLoginFlg = true
        var userInfo: UserId? = null
        while (nonLoginFlg) {
            println("ログインする場合は「1」、新規登録する場合は「2」を入力してください")

            when (readln()) {
                "1" -> {
                    userInfo = signInHandler.signIn()
                    nonLoginFlg = false
                }

                "2" -> {
                    signUpHandler.signUp()
                    nonLoginFlg = false
                }

                else -> println("無効な選択です。もう一度入力してください。")
            }
            println("メニュー選択に戻ります")
        }
        return userInfo
    }
}