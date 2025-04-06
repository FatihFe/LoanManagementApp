package com.loanmanagementapp.data.service

import android.content.Context
import com.loanmanagementapp.data.domain.model.Loan

interface LoanService {
    suspend fun loadLoans(context: Context): List<Loan>
    suspend fun saveLoans(loans: List<Loan>)
}