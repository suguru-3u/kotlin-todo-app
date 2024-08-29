package org.example.presentation.form

data class SignInForm(val userEmail: String, val userPassword: String) {
    override fun toString(): String {
        return "-----入力情報-----\nemail: $userEmail\npassword: $userPassword \n"
    }
}