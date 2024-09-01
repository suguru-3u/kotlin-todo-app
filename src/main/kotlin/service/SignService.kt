package org.example.service

import org.example.domain.repository.SignRepository
import org.example.presentation.form.SignInForm
import org.example.presentation.form.SignUpForm
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignService : KoinComponent {
    private val signRepository: SignRepository by inject()

    fun signIn(signInForm: SignInForm): Long? {
        val result =  signRepository.signIn(signInForm)
        return if(result!!.next()) result.getLong(1) else null;
    }

    fun signUp(signUpForm: SignUpForm){
        signRepository.signUp(signUpForm)
    }
}