package com.loanmanagementapp.presentation.viewmodel

import com.loanmanagementapp.data.preferences.SessionManager
import com.loanmanagementapp.presentation.viewmodel.login.LoginViewModel
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import kotlin.test.Test


class LoginViewModelTest {

    @Mock
    lateinit var sessionManager: SessionManager

    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this) // Begin Mockito
        loginViewModel = LoginViewModel(sessionManager)
    }

    @Test
    fun `onLoginClicked should call saveLoginState`() {
        // Arrange
        val username = "testUser"

        // Act
        loginViewModel.onLoginClicked(username)

        // Assert
        verify(sessionManager).saveLoginState(true, username)
    }
}
