package com.loanmanagementapp.data.domain.strategy.validation

interface ValidationProvider {
    fun validate(text: String): ValidationResult
}