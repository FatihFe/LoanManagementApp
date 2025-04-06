package com.loanmanagementapp.data.domain.model

enum class LoanStatus(val status: String) {
    PAID("paid"),
    OVERDUE("overdue"),
    DEFAULT("default"),
    ACTIVE("active")
}