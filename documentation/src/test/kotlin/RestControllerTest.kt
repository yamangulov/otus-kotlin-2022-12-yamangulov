package ru.otus.otuskotlin.chapi.documentation

import kotlin.test.Test
import kotlin.test.assertEquals

class RestControllerTest {
    @Test
    fun checkResponse() {
        // There will be table name
        val tableName = "tableName"
        // There will be filter realization
        val filter: Filter = Filter()
        // It may be changed in any way
        val offset: Int = 0
        // change to real expected response
        val extpectedResponse: String = "There will be expected response from clickhouse DB"
        val restController = RestController()
        assertEquals(extpectedResponse, restController.getRowResponse(tableName, filter, offset))
    }
}