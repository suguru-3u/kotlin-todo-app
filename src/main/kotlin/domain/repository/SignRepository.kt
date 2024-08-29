package org.example.domain.repository

import org.example.presentation.form.SignInForm
import java.sql.ResultSet

interface SignRepository {
    fun signIn(signInForm: SignInForm): ResultSet?
}