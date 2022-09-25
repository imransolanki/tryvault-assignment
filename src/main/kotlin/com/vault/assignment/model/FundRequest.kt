package com.vault.assignment.model

data class FundRequest(
    val id: String,
    val customer_id: String,
    val load_amount: Double,
    val time: String,
)
