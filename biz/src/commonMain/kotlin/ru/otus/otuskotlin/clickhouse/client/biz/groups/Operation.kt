package ru.otus.otuskotlin.clickhouse.client.biz.groups

import ru.otus.otuskotlin.clickhouse.client.common.ClientContext
import ru.otus.otuskotlin.clickhouse.client.common.models.ClientCommand
import ru.otus.otuskotlin.clickhouse.client.common.models.ClientState
import ru.otus.otuskotlin.clickhouse.client.cor.ICorChainDsl
import ru.otus.otuskotlin.clickhouse.client.cor.chain

fun ICorChainDsl<ClientContext>.operation(title: String, command: ClientCommand, block: ICorChainDsl<ClientContext>.() -> Unit) = chain {
    block()
    this.title = title
    on { this.command == command && state == ClientState.RUNNING }
}
