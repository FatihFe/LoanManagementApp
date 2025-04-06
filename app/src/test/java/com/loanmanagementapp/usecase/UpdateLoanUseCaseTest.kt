package com.loanmanagementapp.usecase

import android.content.Context
import com.loanmanagementapp.data.domain.model.Loan
import com.loanmanagementapp.data.domain.model.LoanStatus
import com.loanmanagementapp.data.domain.strategy.CarLoanStrategy
import com.loanmanagementapp.data.domain.strategy.LoanStrategyFactory
import com.loanmanagementapp.data.domain.strategy.PersonalLoanStrategy
import com.loanmanagementapp.data.domain.usecase.UpdateLoansUseCase
import com.loanmanagementapp.data.repository.LoanRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.*
import kotlin.test.assertEquals

class UpdateLoansUseCaseTest {

    private val loanRepository: LoanRepository = mock(LoanRepository::class.java)
    private val strategyFactory: LoanStrategyFactory = mock(LoanStrategyFactory::class.java)
    private val updateLoansUseCase = UpdateLoansUseCase(loanRepository, strategyFactory)

    @Test
    fun execute_with_valid_loans_updates_them_isCorrect() = runBlocking {
        val context = mock(Context::class.java)
        val loan1 = Loan("Personal", 500.0, 5.0, LoanStatus.ACTIVE.status, 10)
        val loan2 = Loan("Car", 1000.0, 3.0, LoanStatus.ACTIVE.status, 5)

        val loans = listOf(loan1, loan2)

        `when`(loanRepository.getLoans(context)).thenReturn(loans)

        val personalLoanStrategy = mock(PersonalLoanStrategy::class.java)
        val carLoanStrategy = mock(CarLoanStrategy::class.java)

        `when`(strategyFactory.getStrategy(loan1)).thenReturn(personalLoanStrategy)
        `when`(strategyFactory.getStrategy(loan2)).thenReturn(carLoanStrategy)

        `when`(personalLoanStrategy.updateLoan(loan1)).thenReturn(loan1)
        `when`(carLoanStrategy.updateLoan(loan2)).thenReturn(loan2)

        updateLoansUseCase.execute(context)

        verify(loanRepository).saveLoans(loans)
    }

    @Test
    fun calculateTotalAmount_isCorrect() {
        val loan = Loan("Personal", 500.0, 5.0, LoanStatus.ACTIVE.status, 10)

        val totalAmount = updateLoansUseCase.calculateTotalAmount(loan)

        assertEquals(525.0, totalAmount)
    }

    @Test
    fun performCalculationForInterest_isCorrect() {
        val loan = Loan("Personal", 500.0, 5.0, LoanStatus.ACTIVE.status, 10)

        val interest = updateLoansUseCase.performCalculationForInterest(loan)

        assertEquals(25.0, interest)
    }
}
