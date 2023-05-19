package ru.otus.otuskotlin.clickhouse.client.biz.workers

import ru.otus.otuskotlin.clickhouse.client.common.ClientContext
import ru.otus.otuskotlin.clickhouse.client.common.helpers.fail
import ru.otus.otuskotlin.clickhouse.client.common.models.ClientError
import ru.otus.otuskotlin.clickhouse.client.common.models.ClientState
import ru.otus.otuskotlin.clickhouse.client.cor.ICorChainDsl
import ru.otus.otuskotlin.clickhouse.client.cor.worker

fun ICorChainDsl<ClientContext>.stubNoCase(title: String) = worker {
    this.title = title
    on { state == ClientState.RUNNING }
    handle {
        fail(
            ClientError(
                code = "validation",
                field = "stub",
                group = "validation",
                message = "Wrong stub case is requested: ${stubCase.name}"
            )
        )
    }
}
