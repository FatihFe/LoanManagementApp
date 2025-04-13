package com.loanmanagementapp.strategy

import com.loanmanagementapp.data.domain.model.Loan
import com.loanmanagementapp.data.domain.model.LoanType
import com.loanmanagementapp.data.domain.strategy.loan.CarLoanStrategy
import com.loanmanagementapp.data.domain.strategy.loan.DefaultLoanStrategy
import com.loanmanagementapp.data.domain.strategy.loan.LoanStrategyFactory
import com.loanmanagementapp.data.domain.strategy.loan.MortgageLoanStrategy
import com.loanmanagementapp.data.domain.strategy.loan.PersonalLoanStrategy
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import kotlin.test.assertTrue

class LoanStrategyFactoryTest {

    private val loanStrategyFactory = LoanStrategyFactory()

    @Test
    fun personal_Loan_Strategy_isReturned() {
        val personalLoan = mock<Loan> {
            on { name } doReturn LoanType.PERSONAL.type
        }
        val strategy = loanStrategyFactory.getStrategy(personalLoan)
        assertTrue(strategy is PersonalLoanStrategy)
    }

    @Test
    fun car_Loan_Strategy_isReturned() {
        val carLoan = mock<Loan> {
            on { name } doReturn LoanType.CAR.type
        }
        val strategy = loanStrategyFactory.getStrategy(carLoan)
        assertTrue(strategy is CarLoanStrategy)
    }

    @Test
    fun mortgage_Loan_Strategy_isReturned() {
        val mortgageLoan = mock<Loan> {
            on { name } doReturn LoanType.MORTGAGE.type
        }
        val strategy = loanStrategyFactory.getStrategy(mortgageLoan)
        assertTrue(strategy is MortgageLoanStrategy)
    }

    @Test
    fun default_Loan_Strategy_isReturned() {
        val unknownLoan = mock<Loan> {
            on { name } doReturn "Unknown"
        }
        val strategy = loanStrategyFactory.getStrategy(unknownLoan)
        assertTrue(strategy is DefaultLoanStrategy)
    }
}
