package com.loanmanagementapp.di

import com.loanmanagementapp.data.domain.strategy.loan.LoanStrategyFactory
import com.loanmanagementapp.data.repository.LoanRepository
import com.loanmanagementapp.data.repository.LoanRepositoryImpl
import com.loanmanagementapp.data.service.LoanService
import com.loanmanagementapp.data.service.MockLoanService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLoanService(): LoanService = MockLoanService()

    @Provides
    @Singleton
    fun provideLoanRepository(loanService: LoanService): LoanRepository = LoanRepositoryImpl(loanService)

    @Provides
    @Singleton
    fun provideLoanStrategyFactory(): LoanStrategyFactory = LoanStrategyFactory()
}
