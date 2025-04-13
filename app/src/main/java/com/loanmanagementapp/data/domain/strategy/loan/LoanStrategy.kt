package com.loanmanagementapp.data.domain.strategy.loan

import com.loanmanagementapp.data.domain.model.Loan

interface LoanStrategy {
    fun updateLoan(loan: Loan): Loan
}