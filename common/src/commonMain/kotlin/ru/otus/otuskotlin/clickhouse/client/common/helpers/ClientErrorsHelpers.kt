package ru.otus.otuskotlin.clickhouse.client.common.helpers

import ru.otus.otuskotlin.clickhouse.client.common.ClientContext
import ru.otus.otuskotlin.clickhouse.client.common.models.ClientError


fun Throwable.asClientError(
    code: String = "unknown",
    group: String = "exceptions",
    message: String = this.message ?: "",
) = ClientError(
    code = code,
    group = group,
    field = "",
    message = message,
    exception = this,
)

fun ClientContext.addError(vararg error: ClientError) = errors.addAll(error)

fun ClientContext.fail(error: ClientError) {
    addError(error)
    state = ru.otus.otuskotlin.clickhouse.client.common.models.ClientState.FAILING
}