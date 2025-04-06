package com.loanmanagementapp.presentation.screen

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import com.loanmanagementapp.presentation.ui.screen.login.LoginScreen
import com.loanmanagementapp.presentation.viewmodel.login.LoginViewModel
import com.loanmanagementapp.utils.navigateAndClearAllBackStack
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockNavController = mock<NavHostController>()
    private val mockViewModel = mock<LoginViewModel>()

    @Test
    fun testLoginButtonClick() {
        // Set up the composable with mock dependencies
        composeTestRule.setContent {
            LoginScreen(
                navController = mockNavController,
                viewModel = mockViewModel
            )
        }

        // Find the input fields and the login button
        val usernameField = composeTestRule.onNodeWithText("Username")
        val passwordField = composeTestRule.onNodeWithText("Password")
        val loginButton = composeTestRule.onNodeWithText("Login")

        // Type into the fields
        usernameField.performTextInput("testuser")
        passwordField.performTextInput("testpassword")

        // Click the login button
        loginButton.performClick()

        // Verify that the ViewModel's login function is called
        verify(mockViewModel).onLoginClicked("testuser")

        // Verify that the navController navigates to the home screen
        verify(mockNavController).navigateAndClearAllBackStack("home_screen_route")
    }
}
