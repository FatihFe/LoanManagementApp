package com.loanmanagementapp.repository

import android.content.Context
import com.loanmanagementapp.data.domain.model.Loan
import com.loanmanagementapp.data.repository.LoanRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class LoanRepositoryTest {
    @Test
    fun loans_isGet() = runBlocking {
        // Mock context and repository
        val mockContext = mock(Context::class.java)
        val mockLoanRepository = mock(LoanRepository::class.java)

        // Define the mock behavior
        val mockLoans = listOf(
            Loan("Loan A", 1000.0, 5.0, "Active", 12),
            Loan("Loan B", 2000.0, 4.5, "Pending", 24)
        )
        `when`(mockLoanRepository.getLoans(mockContext)).thenReturn(mockLoans)

        // Call the method and assert the result
        val loans = mockLoanRepository.getLoans(mockContext)
        assertEquals("Loan A", loans[0].name)
        assertEquals("Active", loans[0].status)
        assertEquals(12, loans[0].dueIn)
    }

    @Test
    fun loans_isSave() = runBlocking {
        val mockLoanRepository = mock(LoanRepository::class.java)

        // Create sample loans to save
        val loansToSave = listOf(
            Loan("Loan A", 1000.0, 5.0, "Active", 12),
            Loan("Loan B", 2000.0, 4.5, "Pending", 24)
        )

        // Call the method and verify that it was called with correct data
        mockLoanRepository.saveLoans(loansToSave)

        // Verify the interaction with the mock
        verify(mockLoanRepository).saveLoans(loansToSave)
    }
}