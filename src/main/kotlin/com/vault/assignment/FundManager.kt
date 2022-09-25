package com.vault.assignment

class FundManager(private val records: List<FundRequest>) {
    private val mapping = mutableMapOf<String, Account>()

    fun process() {

        records.forEach {

            if (mapping.contains(it.customer_id)) {
                val response = mapping[it.customer_id]?.loadFunds(it, TimePeriod.DAY)
                println(response)
            } else {
                mapping[it.customer_id] = Account()
                val response = mapping[it.customer_id]!!.loadFunds(it, TimePeriod.DAY)
                println(response)
            }

        }

    }

}
