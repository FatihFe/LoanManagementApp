package com.loanmanagementapp.presentation.viewmodel.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.loanmanagementapp.data.domain.strategy.validation.EmailValidationProvider
import com.loanmanagementapp.data.domain.strategy.validation.PasswordValidationProvider
import com.loanmanagementapp.data.domain.strategy.validation.ValidationConfig
import com.loanmanagementapp.data.preferences.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val sessionManager: SessionManager) : ViewModel() {
    private var emailValue by mutableStateOf("")
    private var passwordValue by mutableStateOf("")
    val isButtonClickValid: Boolean
        get() = emailValidationConfig().provider?.validate(emailValue)?.isValid == true &&
                passwordValidationConfig().provider?.validate(passwordValue)?.isValid == true

    fun emailValidationConfig() = ValidationConfig(provider = EmailValidationProvider(), onValueChanged = {
        emailValue = it
    })
    fun passwordValidationConfig() = ValidationConfig(provider = PasswordValidationProvider(), onValueChanged = {
        passwordValue = it
    })
    fun onLoginClicked() = sessionManager.saveLoginState(isLoggedIn = true, username = emailValue)
}