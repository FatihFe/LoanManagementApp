package com.loanmanagementapp.strategy

import com.loanmanagementapp.data.domain.model.Loan
import com.loanmanagementapp.data.domain.model.LoanStatus
import com.loanmanagementapp.data.domain.strategy.CarLoanStrategy
import com.loanmanagementapp.data.domain.strategy.DefaultLoanStrategy
import com.loanmanagementapp.data.domain.strategy.LoanStrategy
import com.loanmanagementapp.data.domain.strategy.MortgageLoanStrategy
import com.loanmanagementapp.data.domain.strategy.PersonalLoanStrategy
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import kotlin.test.assertEquals

class LoanStrategyTest {

    @Test
    fun loan_Strategy_isWork() {
        // Arrange
        val mockLoan = mock(Loan::class.java)
        val loanStrategy = mock(LoanStrategy::class.java)

        // Define the behavior of updateLoan method
        `when`(loanStrategy.updateLoan(mockLoan)).thenReturn(mockLoan)

        // Act
        val result = loanStrategy.updateLoan(mockLoan)

        // Assert
        assertEquals(mockLoan, result)
        verify(loanStrategy).updateLoan(mockLoan)
    }

    @Test
    fun personalLoanStrategy_updates_loan_isCorrect() {
        val loan = Loan("Personal", 500.0, 5.0, LoanStatus.ACTIVE.status, 10)
        val personalLoanStrategy = PersonalLoanStrategy()

        val updatedLoan = personalLoanStrategy.updateLoan(loan)

        // The interest rate should increase by 0.5
        assertEquals(5.5, updatedLoan.interestRate)
    }

    @Test
    fun carLoanStrategy_updates_loan_isCorrect() {
        val loan = Loan("Car", 1500.0, 4.0, LoanStatus.ACTIVE.status, 10)
        val carLoanStrategy = CarLoanStrategy()

        val updatedLoan = carLoanStrategy.updateLoan(loan)

        // The interest rate should increase by 0.3
        assertEquals(4.3, updatedLoan.interestRate)
    }

    @Test
    fun mortgageLoanStrategy_updates_loan_isCorrect() {
        val loan = Loan("Mortgage", 200000.0, 3.0, LoanStatus.ACTIVE.status, 30)
        val mortgageLoanStrategy = MortgageLoanStrategy()

        val updatedLoan = mortgageLoanStrategy.updateLoan(loan)

        // The interest rate should remain unchanged
        assertEquals(3.0, updatedLoan.interestRate)
    }

    @Test
    fun defaultLoanStrategy_returns_loan_isChanged() {
        val loan = Loan("Personal", 500.0, 5.0, LoanStatus.PAID.status, 10)
        val defaultLoanStrategy = DefaultLoanStrategy()

        val updatedLoan = defaultLoanStrategy.updateLoan(loan)

        // No update should occur, so the loan remains unchanged
        assertEquals(5.0, updatedLoan.interestRate)
    }
}
