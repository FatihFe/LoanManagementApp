package com.loanmanagementapp

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.loanmanagementapp.data.preferences.SessionManager
import com.loanmanagementapp.presentation.ui.components.CustomTopAppBar
import com.loanmanagementapp.presentation.ui.navigation.AppNavGraph
import com.loanmanagementapp.presentation.ui.theme.LoanManagementAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           LoanApp()
        }
    }
}

@Composable
fun LoanApp() {
    LoanManagementAppTheme {
        val context = LocalContext.current
        val navController = rememberNavController()
        val sessionManager = remember { SessionManager(context) }
        Scaffold(topBar = {
            CustomTopAppBar(navController = navController, context = context)
        }) { paddingValues ->
            AppNavGraph(
                modifier = Modifier.padding(paddingValues),
                navController = navController,
                sessionManager = sessionManager
            )
        }
    }
}
