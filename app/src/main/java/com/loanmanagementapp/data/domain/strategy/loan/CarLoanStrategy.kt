package com.loanmanagementapp.data.domain.strategy.loan

import com.loanmanagementapp.data.domain.model.Loan
import com.loanmanagementapp.data.domain.model.LoanStatus
import com.loanmanagementapp.utils.updateStatus

class CarLoanStrategy : LoanStrategy {
    override fun updateLoan(loan: Loan): Loan {
        var updatedLoan = updateStatus(loan)

        // If the loan is not paid, increase the interest rate by 0.3
        if (updatedLoan.status != LoanStatus.PAID.status) {
            updatedLoan = updatedLoan.copy(
                interestRate = updatedLoan.interestRate + 0.3
            )
        }

        return updatedLoan
    }
}