package com.vault.assignment

data class FundRequest(
    val id: String,
    val customer_id: String,
    val load_amount: Double,
    val time: String,
)
