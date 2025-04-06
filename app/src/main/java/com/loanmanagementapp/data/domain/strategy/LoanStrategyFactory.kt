package com.loanmanagementapp.data.domain.strategy

import com.loanmanagementapp.data.domain.model.Loan
import com.loanmanagementapp.data.domain.model.LoanType

class LoanStrategyFactory {
    fun getStrategy(loan: Loan): LoanStrategy {
        return when (loan.name) {
            LoanType.PERSONAL.type -> PersonalLoanStrategy()
            LoanType.CAR.type -> CarLoanStrategy()
            LoanType.MORTGAGE.type -> MortgageLoanStrategy()
            else -> DefaultLoanStrategy()
        }
    }
}