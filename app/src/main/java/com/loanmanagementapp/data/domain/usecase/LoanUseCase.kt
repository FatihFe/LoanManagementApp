package com.loanmanagementapp.data.domain.usecase

import android.content.Context
import com.loanmanagementapp.data.domain.model.Loan
import com.loanmanagementapp.data.domain.strategy.loan.LoanStrategyFactory
import com.loanmanagementapp.data.repository.LoanRepository
import javax.inject.Inject

class UpdateLoansUseCase @Inject constructor(
    private val loanRepository: LoanRepository,
    private val strategyFactory: LoanStrategyFactory
) {
    suspend fun execute(context: Context): List<Loan> {
        val loans = loanRepository.getLoans(context).map { loan ->
            val strategy = strategyFactory.getStrategy(loan)
            strategy.updateLoan(loan)
        }
        loanRepository.saveLoans(loans)
        return loans
    }

    fun calculateTotalAmount(loan: Loan): Double {
        return loan.principalAmount * (1 + loan.interestRate / 100)
    }

    fun performCalculationForInterest(loan: Loan) : Double {
        return (loan.principalAmount * loan.interestRate / 100)
    }
}