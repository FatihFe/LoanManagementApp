package com.loanmanagementapp.data.domain.strategy.validation

import android.util.Patterns

class EmailValidationProvider : ValidationProvider {
    override fun validate(text: String): ValidationResult {
        return ValidationResult.validateRegex(text, Patterns.EMAIL_ADDRESS.pattern())
    }
}