package ru.otus.otuskotlin.clickhouse.client.common.repo

import ru.otus.otuskotlin.clickhouse.client.common.models.ClientError

interface IDbResponse<T> {
    val data: T?
    val isSuccess: Boolean
    val errors: List<ClientError>
}