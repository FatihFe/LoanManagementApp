package com.loanmanagementapp.presentation.ui.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.loanmanagementapp.R
import com.loanmanagementapp.data.domain.model.TextConfig
import com.loanmanagementapp.presentation.ui.components.FormFieldView
import com.loanmanagementapp.presentation.ui.components.PrimaryButton
import com.loanmanagementapp.presentation.ui.navigation.Screen
import com.loanmanagementapp.presentation.ui.theme.titleLarge
import com.loanmanagementapp.presentation.viewmodel.login.LoginViewModel
import com.loanmanagementapp.utils.navigateAndClearAllBackStack

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.login_screen_login_title),
                style = titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        FormFieldView(
            config = TextConfig(
                title = stringResource(R.string.login_screen_email_title),
                placeholder = stringResource(R.string.login_screen_email_placeholder),
                errorMessage = stringResource(R.string.login_screen_email_error),
            ),
            validationConfig = viewModel.emailValidationConfig()
        )
        FormFieldView(
            config = TextConfig(
                title = stringResource(R.string.login_screen_password_title),
                placeholder = stringResource(R.string.login_screen_password_placeholder),
                errorMessage = stringResource(R.string.login_screen_password_error),
            ),
            validationConfig = viewModel.passwordValidationConfig()
        )
        Spacer(modifier = Modifier.height(20.dp))
        PrimaryButton(
            onClick = {
                viewModel.onLoginClicked()
                navController.navigateAndClearAllBackStack(Screen.HomeScreen.route)
            }, text = stringResource(R.string.login_screen_login_button),
            enabled = viewModel.isButtonClickValid
        )
    }
}