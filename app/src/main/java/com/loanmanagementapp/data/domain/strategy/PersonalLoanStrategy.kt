package com.loanmanagementapp.data.domain.strategy

import com.loanmanagementapp.data.domain.model.Loan
import com.loanmanagementapp.data.domain.model.LoanStatus
import com.loanmanagementapp.utils.updateStatus

class PersonalLoanStrategy : LoanStrategy {
    override fun updateLoan(loan: Loan): Loan {
        // Update the loan's status based on general criteria
        var updatedLoan = updateStatus(loan)

        // Increase interest rate if loan is not PAID or DEFAULT and has days remaining
        if (shouldIncreaseInterest(updatedLoan)) {
            updatedLoan = updatedLoan.copy(interestRate = updatedLoan.interestRate + 0.5)
        }

        // Update loan status based on principal amount and overdue condition
        updatedLoan = when {
            // Mark as PAID if principal amount is less than 1000
            updatedLoan.principalAmount < 1000 -> updatedLoan.copy(status = LoanStatus.PAID.status)

            // Mark as DEFAULT if loan is overdue and principal is greater than 5000
            isOverdueAndHighAmount(updatedLoan) -> updatedLoan.copy(status = LoanStatus.DEFAULT.status)
            else -> updatedLoan
        }

        return updatedLoan
    }

    // Returns true if interest rate should be increased based on loan status and due days
    private fun shouldIncreaseInterest(loan: Loan): Boolean {
        return loan.status !in listOf(LoanStatus.PAID.status, LoanStatus.DEFAULT.status) && loan.dueIn > 0
    }

    // Returns true if loan is overdue and the principal amount is high
    private fun isOverdueAndHighAmount(loan: Loan): Boolean {
        return loan.status == LoanStatus.OVERDUE.status && loan.principalAmount > 5000
    }
}