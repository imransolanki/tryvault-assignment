package com.vault.assignment

import com.google.gson.Gson
import com.vault.assignment.model.FundRequest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.core.io.ClassPathResource

@TestConfiguration
class BulkFundsTests {

    @Test
    fun `process records in bulk and print output`() {
        val fundManager = FundManager(parseRecords("input.txt"))
        fundManager.process()
    }

    private fun parseRecords(filePath: String): List<FundRequest> {
        val list = mutableListOf<FundRequest>()

        val lines = ClassPathResource("input.txt").file.readLines()

        lines.forEach {
            val request = Gson().fromJson(it, FundRequest::class.java)
            list.add(request)
        }

        return list
    }
}