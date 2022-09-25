package com.vault.assignment

import com.vault.assignment.model.FundRequest
import com.vault.assignment.model.FundResponse

class Account : Limit {

    private var dailyLimit = getLimit(TimePeriod.DAY)
    private var weeklyLimit = getLimit(TimePeriod.WEEK)

    private var maxLoadsAttempt = 3

    fun loadFunds(request: FundRequest, period: TimePeriod): FundResponse {

        when (period) {
            TimePeriod.DAY -> dailyLimit -= request.load_amount
            TimePeriod.WEEK -> weeklyLimit -= request.load_amount
        }

        maxLoadsAttempt -= 1

        return FundResponse(
            id = request.id,
            customer_id = request.customer_id,
            accepted = dailyLimit >= 0 && maxLoadsAttempt >= 0
        )
    }

    override fun getLimit(period: TimePeriod): Double = when (period) {
        TimePeriod.DAY -> 5000.0
        TimePeriod.WEEK -> 20000.0
    }
}
