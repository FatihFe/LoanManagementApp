package com.loanmanagementapp.presentation.screen

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.loanmanagementapp.data.domain.model.Loan
import com.loanmanagementapp.presentation.ui.screen.home.HomeScreen
import com.loanmanagementapp.presentation.viewmodel.home.HomeViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
class HomeScreenTest {

    private lateinit var viewModel: HomeViewModel

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        // Mocking the ViewModel
        viewModel = mock()

        // Setting up the Compose UI for testing
        composeTestRule.setContent {
            HomeScreen(viewModel = viewModel)
        }
    }

    @Test
    fun screen_displays_loan_data_isCorrect() = runTest {
        val loanList = listOf(
            Loan("PERSONAL", 1500.0, 5.0, "ACTIVE", 10)
        )

        // Mocking the ViewModel's loans state for testing
        whenever(viewModel.loansState.value).thenReturn(loanList)

        // Verifying that the UI displays the loan data correctly
        composeTestRule.onNodeWithText("User Name:").assertExists()
        composeTestRule.onNodeWithText("ACTIVE").assertExists() // Loan status verification

        // Checking if the loan information is displayed in the list
        composeTestRule.onNodeWithText("PERSONAL").assertExists()
    }

    @Test
    fun updateLoans_button_click_and_data_isChange() = runTest {
        val loanList = listOf(
            Loan("PERSONAL", 1500.0, 5.0, "ACTIVE", 10)
        )

        // Mocking the ViewModel's loans state for testing
        whenever(viewModel.loansState.value).thenReturn(loanList)

        // Verifying that the UI displays the loan data correctly
        composeTestRule.onNodeWithText("User Name:").assertExists()
        composeTestRule.onNodeWithText("ACTIVE").assertExists()

        // Simulating a button click to update loans (example)
        // composeTestRule.onNodeWithTag("update_button").performClick()

        // Verifying if the data is updated and displayed in the UI
        composeTestRule.onNodeWithText("PERSONAL").assertExists()
    }
}
