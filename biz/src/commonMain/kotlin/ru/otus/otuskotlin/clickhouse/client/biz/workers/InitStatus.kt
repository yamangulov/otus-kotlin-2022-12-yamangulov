package ru.otus.otuskotlin.clickhouse.client.biz.workers

import ru.otus.otuskotlin.clickhouse.client.common.ClientContext
import ru.otus.otuskotlin.clickhouse.client.common.models.ClientState
import ru.otus.otuskotlin.clickhouse.client.cor.ICorChainDsl
import ru.otus.otuskotlin.clickhouse.client.cor.worker

fun ICorChainDsl<ClientContext>.initStatus(title: String) = worker() {
    this.title = title
    on { state == ClientState.NONE }
    handle { state = ClientState.RUNNING }
}
