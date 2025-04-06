package com.loanmanagementapp.presentation.ui.components

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.loanmanagementapp.R
import com.loanmanagementapp.data.preferences.SessionManager
import com.loanmanagementapp.presentation.ui.navigation.Screen
import com.loanmanagementapp.presentation.ui.navigation.screens
import com.loanmanagementapp.utils.navigateAndClearAllBackStack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    navController: NavHostController,
    context: Context
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val screen = screens.find { it.route == currentRoute }
    val sessionManager = remember { SessionManager(context) }
    if (screen != null && screen.isShowTopBar) {
        val showBackButton = navController.previousBackStackEntry != null && screen.isShowBackIcon
        val showLogOutButton = sessionManager.isLoggedIn() && screen.isShowLogOutIcon
        val title = screen.screenTitleResId?.let { stringResource(id = it) }.orEmpty()
        var showBottomSheet by remember { mutableStateOf(false) }

        TopAppBar(
            modifier = Modifier.shadow(4.dp, RoundedCornerShape(0.dp), clip = false),
            title = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = title,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            },
            navigationIcon = {
                if (showBackButton) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            },
            actions = {
                if (showLogOutButton) {
                    IconButton(onClick = {
                        showBottomSheet = true
                    }) {
                        Icon(Icons.Default.ExitToApp, contentDescription = null)
                    }
                }
            }
        )

        if (showBottomSheet) {
            CustomBottomSheet(onDismiss = {
                showBottomSheet = false
            }, onContinue = {
                showBottomSheet = false
                sessionManager.logout()
                navController.navigateAndClearAllBackStack(Screen.LoginScreen.route)
            }, title = stringResource(R.string.custom_top_app_bar_bottom_sheet_title),
                approveButtonTitle = stringResource(R.string.custom_top_app_bar_bottom_sheet_approved_button),
                unApproveButtonTitle = stringResource(R.string.custom_top_app_bar_bottom_sheet_unapproved_button)
            )
        }
    }
}