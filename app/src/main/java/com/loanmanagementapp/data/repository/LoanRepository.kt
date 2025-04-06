package com.loanmanagementapp.data.repository

import android.content.Context
import com.loanmanagementapp.data.domain.model.Loan

interface LoanRepository {
    suspend fun getLoans(context: Context): List<Loan>
    suspend fun saveLoans(loans: List<Loan>)
}