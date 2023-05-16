package ru.otus.otuskotlin.clickhouse.client.biz.workers

import ru.otus.otuskotlin.clickhouse.client.common.ClientContext
import ru.otus.otuskotlin.clickhouse.client.common.models.ClientError
import ru.otus.otuskotlin.clickhouse.client.common.models.ClientState
import ru.otus.otuskotlin.clickhouse.client.common.stubs.ClientStubs
import ru.otus.otuskotlin.clickhouse.client.cor.ICorChainDsl
import ru.otus.otuskotlin.clickhouse.client.cor.worker

fun ICorChainDsl<ClientContext>.stubDbError(title: String) = worker {
    this.title = title
    on { stubCase == ClientStubs.DB_ERROR && state == ClientState.RUNNING }
    handle {
        state = ClientState.FAILING
        this.errors.add(
            ClientError(
                group = "internal",
                code = "internal-db",
                message = "Internal error"
            )
        )
    }
}
