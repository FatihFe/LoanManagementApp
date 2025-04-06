package com.loanmanagementapp.presentation.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loanmanagementapp.R
import com.loanmanagementapp.data.preferences.SessionManager
import com.loanmanagementapp.presentation.ui.components.LoanItem
import com.loanmanagementapp.presentation.ui.theme.bodyLargeBold
import com.loanmanagementapp.presentation.viewmodel.home.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val loansState by viewModel.loansState.collectAsState()
    val sessionManager = remember { SessionManager(context) }
    LaunchedEffect(Unit) {
        viewModel.updateLoans(context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Text(text = stringResource(R.string.home_screen_username), style = bodyLargeBold)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = sessionManager.getUsername().orEmpty(), style = bodyLargeBold)
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(loansState) { loan ->
                LoanItem(
                    loan = loan,
                    totalAmount = viewModel.getTotalAmount(loan),
                    interestAmount = viewModel.getInterest(loan)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}