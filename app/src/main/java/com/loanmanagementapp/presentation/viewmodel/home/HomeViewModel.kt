package com.loanmanagementapp.presentation.viewmodel.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loanmanagementapp.data.domain.usecase.UpdateLoansUseCase
import com.loanmanagementapp.data.domain.model.Loan
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val updateLoansUseCase: UpdateLoansUseCase) : ViewModel() {
    private val _loansState = MutableStateFlow<List<Loan>>(emptyList())
    val loansState: StateFlow<List<Loan>> get() = _loansState

    fun updateLoans(context: Context) {
        viewModelScope.launch {
            try {
                val updatedLoans = updateLoansUseCase.execute(context)
                _loansState.value = updatedLoans
            } catch (e: Exception) {
                // Error management
            }
        }
    }

    fun getTotalAmount(loan: Loan): Double {
        return updateLoansUseCase.calculateTotalAmount(loan)
    }

    fun getInterest(loan: Loan): Double {
        return updateLoansUseCase.performCalculationForInterest(loan)
    }
}