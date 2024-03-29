package ru.otus.otuskotlin.clickhouse.client.common.models

data class ClientError(
    val code: String = "",
    val group: String = "",
    val field: String = "",
    val message: String = "",
    val exception: Throwable? = null,
)
