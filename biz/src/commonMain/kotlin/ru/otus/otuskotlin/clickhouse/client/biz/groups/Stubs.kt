package ru.otus.otuskotlin.clickhouse.client.biz.groups

import ru.otus.otuskotlin.clickhouse.client.common.ClientContext
import ru.otus.otuskotlin.clickhouse.client.common.models.ClientState
import ru.otus.otuskotlin.clickhouse.client.common.models.ClientWorkMode
import ru.otus.otuskotlin.clickhouse.client.cor.ICorChainDsl
import ru.otus.otuskotlin.clickhouse.client.cor.chain


fun ICorChainDsl<ClientContext>.stubs(title: String, block: ICorChainDsl<ClientContext>.() -> Unit) = chain {
    block()
    this.title = title
    on { workMode == ClientWorkMode.STUB && state == ClientState.RUNNING }
}
