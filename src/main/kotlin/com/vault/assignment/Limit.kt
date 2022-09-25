package com.vault.assignment

interface Limit {
    fun getLimit(period: TimePeriod): Double
}