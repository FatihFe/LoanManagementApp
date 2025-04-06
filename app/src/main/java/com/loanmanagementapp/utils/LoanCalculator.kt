package com.loanmanagementapp.utils

import com.loanmanagementapp.data.domain.model.Loan
import com.loanmanagementapp.data.domain.model.LoanStatus

fun updateStatus(loan: Loan): Loan {
    val updatedLoan = loan.copy()
    updatedLoan.dueIn -= 1

    // If the loan is past due and not already marked as PAID
    if (updatedLoan.dueIn < 0 && updatedLoan.status != LoanStatus.PAID.status) {
        // Update the status based on the remaining principal amount
        updatedLoan.status = when {
            updatedLoan.principalAmount > 0 -> LoanStatus.OVERDUE.status // Loan is overdue but still has outstanding amount
            else -> LoanStatus.DEFAULT.status // No principal left, considered default
        }
    }
    return updatedLoan
}