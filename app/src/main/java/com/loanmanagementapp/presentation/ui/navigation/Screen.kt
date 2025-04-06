package com.loanmanagementapp.presentation.ui.navigation

import com.loanmanagementapp.R

sealed class Screen(
    val route: String,
    val screenTitleResId: Int? = null,
    val isShowTopBar: Boolean = false,
    val isShowBackIcon: Boolean = false,
    val isShowLogOutIcon: Boolean = false,
) {
    data object LoginScreen : Screen(
        route = "login_screen"
    )

    data object HomeScreen : Screen(
        route = "home_screen",
        isShowTopBar = true,
        screenTitleResId = R.string.home_screen_title,
        isShowBackIcon = true,
        isShowLogOutIcon = true
    )
}

val screens = listOf(
    Screen.LoginScreen,
    Screen.HomeScreen,
)