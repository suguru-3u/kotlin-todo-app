package org.example.presentation.handler

import org.example.domain.model.User
import org.example.domain.model.UserId
import org.example.presentation.form.SignInForm
import org.example.service.SignService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignInHandler : KoinComponent {

    private val signService: SignService by inject()

    fun signIn(): UserId? {
        println("登録しているemailを入力してください")
        val inputEmail = readln()
        println("登録しているpasswordを入力してください")
        val inputPassword = readln()

        val signInForm = SignInForm(inputEmail, inputPassword)
        println("こちらの内容で登録状況を確認します\n $signInForm")

        return runCatching { signService.signIn(signInForm) }
            .onFailure { exception ->
                println("サインインに失敗しました: ${exception.message}")
            }
            .getOrNull()?.let { UserId(it) }
    }
}