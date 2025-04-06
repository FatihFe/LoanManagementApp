package com.loanmanagementapp.data.repository
import android.content.Context
import com.loanmanagementapp.data.domain.model.Loan
import com.loanmanagementapp.data.service.LoanService
import javax.inject.Inject

class LoanRepositoryImpl @Inject constructor(private val loanService: LoanService) : LoanRepository {
    override suspend fun getLoans(context: Context): List<Loan> {
        return loanService.loadLoans(context)
    }

    override suspend fun saveLoans(loans: List<Loan>) {
        loanService.saveLoans(loans)
    }
}