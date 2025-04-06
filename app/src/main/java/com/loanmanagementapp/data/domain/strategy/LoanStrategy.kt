package com.loanmanagementapp.data.domain.strategy

import com.loanmanagementapp.data.domain.model.Loan

interface LoanStrategy {
    fun updateLoan(loan: Loan): Loan
}