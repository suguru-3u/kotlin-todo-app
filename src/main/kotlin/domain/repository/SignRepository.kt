package org.example.domain.repository

import org.example.presentation.form.SignInForm
import org.example.presentation.form.SignUpForm
import java.sql.ResultSet

interface SignRepository {
    fun signIn(signInForm: SignInForm): ResultSet?
    fun signUp(signUpForm: SignUpForm)
}