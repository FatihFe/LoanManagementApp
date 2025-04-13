package com.loanmanagementapp.data.domain.strategy.validation

class PasswordValidationProvider : ValidationProvider {
    override fun validate(text: String): ValidationResult {
        val passwordRegex = Regex("^(?=.*[a-z])(?=.*[A-Z]).{6,}$")
        return ValidationResult.validateRegex(text, passwordRegex.pattern)
    }
}