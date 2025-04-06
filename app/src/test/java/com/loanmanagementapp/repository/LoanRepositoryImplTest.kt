package com.loanmanagementapp.repository

import android.content.Context
import com.loanmanagementapp.data.domain.model.Loan
import com.loanmanagementapp.data.domain.model.LoanStatus
import com.loanmanagementapp.data.repository.LoanRepositoryImpl
import com.loanmanagementapp.data.service.LoanService
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.*
import kotlin.test.assertEquals

class LoanRepositoryImplTest {

    private val loanService: LoanService = mock(LoanService::class.java)
    private val loanRepository = LoanRepositoryImpl(loanService)

    @Test
    fun getLoans_returns_loans_from_loanService() = runBlocking {
        val context = mock(Context::class.java)
        val loans = listOf(
            Loan("Personal", 500.0, 5.0, LoanStatus.ACTIVE.status, 10),
            Loan("Car", 1000.0, 3.0, LoanStatus.ACTIVE.status, 5)
        )

        `when`(loanService.loadLoans(context)).thenReturn(loans)

        val result = loanRepository.getLoans(context)

        assertEquals(loans, result)
    }

    @Test
    fun saveLoans_calls_loanService() = runBlocking {
        val loans = listOf(
            Loan("Personal", 500.0, 5.0, LoanStatus.ACTIVE.status, 10),
            Loan("Car", 1000.0, 3.0, LoanStatus.ACTIVE.status, 5)
        )

        loanRepository.saveLoans(loans)

        verify(loanService).saveLoans(loans)
    }
}
