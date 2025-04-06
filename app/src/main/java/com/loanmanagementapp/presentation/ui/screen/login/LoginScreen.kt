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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.loanmanagementapp.R
import com.loanmanagementapp.presentation.ui.components.CustomOutlinedTextField
import com.loanmanagementapp.presentation.ui.components.PrimaryButton
import com.loanmanagementapp.presentation.ui.navigation.Screen
import com.loanmanagementapp.presentation.ui.theme.bodyLarge
import com.loanmanagementapp.presentation.ui.theme.titleLarge
import com.loanmanagementapp.presentation.viewmodel.login.LoginViewModel
import com.loanmanagementapp.utils.navigateAndClearAllBackStack

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoginButtonClicked by remember { mutableStateOf(false) }

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
        Text(text = stringResource(R.string.login_screen_username_title), style = bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        CustomOutlinedTextField(
            value = username,
            onValueChange = { username = it },
            placeholderText = stringResource(R.string.login_screen_username_placeholder),
            trailingIconId = R.drawable.ic_login_username,
            actionState = isLoginButtonClicked
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(R.string.login_screen_password_title), style = bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        CustomOutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholderText = stringResource(R.string.login_screen_password_placeholder),
            visualTransformation = PasswordVisualTransformation(),
            trailingIconId = R.drawable.ic_login_password,
            actionState = isLoginButtonClicked
        )
        Spacer(modifier = Modifier.height(20.dp))
        PrimaryButton(onClick = {
            isLoginButtonClicked = true
            if (username.isNotBlank() && password.isNotBlank()) {
                viewModel.onLoginClicked(username = username)
                navController.navigateAndClearAllBackStack(Screen.HomeScreen.route)
            }
        }, text = stringResource(R.string.login_screen_login_button))
    }
}