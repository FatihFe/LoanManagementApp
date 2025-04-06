package com.loanmanagementapp.presentation.viewmodel.login

import androidx.lifecycle.ViewModel
import com.loanmanagementapp.data.preferences.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val sessionManager: SessionManager) : ViewModel() {
    fun onLoginClicked(username: String) {
        sessionManager.saveLoginState(isLoggedIn = true, username = username)
    }
}