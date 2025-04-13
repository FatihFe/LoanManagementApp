package com.loanmanagementapp.data.domain.strategy.validation

sealed class ValidationResult {
    object Success : ValidationResult()
    data class Failure(val message: String) : ValidationResult()

    val isValid: Boolean
        get() = this is Success

    val errorMessage: String?
        get() = (this as? Failure)?.message

    companion object {
        fun validateRegex(text: String?, regex: String): ValidationResult {
            if (text == null) return Failure("Input is null")
            return if (Regex(regex).matches(text)) {
                Success
            } else {
                Failure("Invalid format")
            }
        }
    }
}