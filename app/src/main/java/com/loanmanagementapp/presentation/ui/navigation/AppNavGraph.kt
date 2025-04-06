package com.loanmanagementapp.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.loanmanagementapp.data.preferences.SessionManager
import com.loanmanagementapp.presentation.ui.screen.home.HomeScreen
import com.loanmanagementapp.presentation.ui.screen.login.LoginScreen

@Composable
fun AppNavGraph(
    modifier: Modifier,
    navController: NavHostController,
    sessionManager: SessionManager
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = if (sessionManager.isLoggedIn()) Screen.HomeScreen.route else Screen.LoginScreen.route
    ) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen()
        }
    }
}