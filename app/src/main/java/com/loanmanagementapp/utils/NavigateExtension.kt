package com.loanmanagementapp.utils

import androidx.navigation.NavController

fun NavController.navigateAndClearAllBackStack(route: String) {
    navigate(route) {
        popUpTo(0) {
            inclusive = true
        }
        launchSingleTop = true
    }
}