package com.vault.assignment


import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.util.AssertionErrors.assertEquals

@SpringBootTest
class BasicFundsTests {

    @Test
    fun `A maximum of $5,000 can be loaded per day`() {
        val account = Account()

        val request = FundRequest(
            id = "1234",
            customer_id = "1234",
            load_amount = 5000.00,
            time = "2018-01-01T00:00:00Z"
        )

        val response = account.loadFunds(request, TimePeriod.DAY)

        assertEquals("Request 1", response.id, request.id)
        assertEquals("Request 1", response.customer_id, request.customer_id)
        assertEquals("Request 1", response.accepted, true)
    }

    @Test
    internal fun `a user attempting to load $3,000 twice in one day`() {
        val account = Account()

        val request = FundRequest(
            id = "1234",
            customer_id = "1234",
            load_amount = 3000.00,
            time = "2018-01-01T00:00:00Z"
        )

        var response = account.loadFunds(request, TimePeriod.DAY)

        assertEquals("Request 1", response.id, request.id)
        assertEquals("Request 1", response.customer_id, request.customer_id)
        assertEquals("Request 1", response.accepted, true)


        response = account.loadFunds(request.copy(load_amount = 3000.00), TimePeriod.DAY)

        assertEquals("Request 2", response.id, request.id)
        assertEquals("Request 2", response.customer_id, request.customer_id)
        assertEquals("Request 2", response.accepted, false)
    }

    @Test
    internal fun `user attempting to load $400 four times in a day`() {
        val account = Account()

        val request = FundRequest(
            id = "1234",
            customer_id = "1234",
            load_amount = 400.00,
            time = "2018-01-01T00:00:00Z"
        )

        var response = account.loadFunds(request, TimePeriod.DAY)
        assertEquals("Request 1", response.accepted, true)

        response = account.loadFunds(request.copy(load_amount = 400.00), TimePeriod.DAY)
        assertEquals("Request 2", response.accepted, true)

        response = account.loadFunds(request.copy(load_amount = 400.00), TimePeriod.DAY)
        assertEquals("Request 3", response.accepted, true)

        response = account.loadFunds(request.copy(load_amount = 400.00), TimePeriod.DAY)
        assertEquals("Request 4", response.accepted, false)

    }
}
