package org.example.presentation.form

data class SignUpForm(val email: String, val password: String) {
    init {
        checkEmail(email)
    }

    private fun checkEmail(email: String) {
        println(email)
    }
}
