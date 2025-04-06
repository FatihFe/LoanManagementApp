package com.loanmanagementapp.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.loanmanagementapp.R
import com.loanmanagementapp.data.domain.model.Loan
import com.loanmanagementapp.presentation.ui.theme.bodyLarge
import com.loanmanagementapp.presentation.ui.theme.bodyLargeBold
import com.loanmanagementapp.utils.formatAmount

@Composable
fun LoanItem(loan: Loan, totalAmount: Double, interestAmount: Double) {
    LoanCardView(title = loan.name) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row {
                Text(text = stringResource(R.string.home_screen_loan_status), style = bodyLarge)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = loan.status, style = bodyLarge)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Text(text = stringResource(R.string.home_screen_loan_principal), style = bodyLarge)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = loan.principalAmount.formatAmount(), style = bodyLarge)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Text(text = stringResource(R.string.home_screen_loan_interest_rate), style = bodyLarge)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = loan.interestRate.toString(), style = bodyLarge)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Text(text = stringResource(R.string.home_screen_loan_interest_amount), style = bodyLarge)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = interestAmount.formatAmount(), style = bodyLarge)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Text(text = stringResource(R.string.home_screen_loan_total_amount), style = bodyLargeBold)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = totalAmount.formatAmount(), style = bodyLargeBold)
            }
        }
    }
}