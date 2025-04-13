package com.loanmanagementapp.data.domain.strategy.loan

import com.loanmanagementapp.data.domain.model.Loan
import com.loanmanagementapp.utils.updateStatus

class MortgageLoanStrategy : LoanStrategy {
    override fun updateLoan(loan: Loan): Loan {
        return updateStatus(loan)
    }
}