package com.loanmanagementapp.presentation.viewmodel

import com.loanmanagementapp.data.domain.model.Loan
import com.loanmanagementapp.data.domain.usecase.UpdateLoansUseCase
import com.loanmanagementapp.presentation.viewmodel.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var updateLoansUseCase: UpdateLoansUseCase

    @Before
    fun setUp() {
        // Mocking required dependencies
        updateLoansUseCase = mock()
        viewModel = HomeViewModel(updateLoansUseCase)
    }

    @Test
    fun updates_loans_state_isCorrectly() = runTest {
        val loanList = listOf(
            Loan("PERSONAL", 1500.0, 5.0, "ACTIVE", 10)
        )

        // Ensuring that UseCase returns the correct data
        whenever(updateLoansUseCase.execute(any())).thenReturn(loanList)

        // Triggering the updateLoans function in the ViewModel
        viewModel.updateLoans(mock())

        // Verifying that the state is correctly updated
        assertEquals(loanList, viewModel.loansState.value)
    }
}
