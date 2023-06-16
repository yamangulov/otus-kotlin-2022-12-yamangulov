package ru.otus.otuskotlin.clickhouse.client.common.repo

import ru.otus.otuskotlin.clickhouse.client.common.models.ClientError
import ru.otus.otuskotlin.clickhouse.client.common.models.ClientEstate

data class DbClientEstatesResponse(
    override val data: List<ClientEstate>?,
    override val isSuccess: Boolean,
    override val errors: List<ClientError> = emptyList(),
): IDbResponse<List<ClientEstate>> {
    companion object {
        val MOCK_SUCCESS_EMPTY = DbClientEstatesResponse(emptyList(), true)
        fun success(result: List<ClientEstate>) = DbClientEstatesResponse(result, true)
        fun error(errors: List<ClientError>) = DbClientEstatesResponse(null, false, errors)
        fun error(error: ClientError) = DbClientEstatesResponse(null, false, listOf(error))
    }
}
