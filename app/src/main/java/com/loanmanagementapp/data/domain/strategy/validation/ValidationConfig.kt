package com.loanmanagementapp.data.domain.strategy.validation

data class ValidationConfig(
    val provider: ValidationProvider?,
    val onValueChanged: (String) -> Unit
)