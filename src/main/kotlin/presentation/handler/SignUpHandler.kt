package org.example.presentation.handler

import org.example.presentation.form.SignInForm
import org.example.presentation.form.SignUpForm
import org.example.service.SignService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignUpHandler : KoinComponent {

    val signService: SignService by inject()

    fun signUp() {
        println("ユーザーの新規登録を開始します。")
        println("登録するemailを入力してください")
        val inputEmail = readln()
        println("登録するpasswordを入力してください")
        val inputPassword = readln()

        val signUpForm = SignUpForm(inputEmail, inputPassword)
        println("こちらの内容で登録します\n $signUpForm")
    }
}