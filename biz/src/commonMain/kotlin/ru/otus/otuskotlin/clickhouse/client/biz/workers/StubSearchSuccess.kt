package ru.otus.otuskotlin.clickhouse.client.biz.workers

import ru.otus.otuskotlin.clickhouse.client.common.ClientContext
import ru.otus.otuskotlin.clickhouse.client.common.models.ClientState
import ru.otus.otuskotlin.clickhouse.client.common.stubs.ClientStubs
import ru.otus.otuskotlin.clickhouse.client.cor.ICorChainDsl
import ru.otus.otuskotlin.clickhouse.client.cor.worker
import ru.otus.otuskotlin.clickhouse.client.stubs.v1.ClientEstateStub

fun ICorChainDsl<ClientContext>.stubSearchSuccess(title: String) = worker {
    this.title = title
    on { stubCase == ClientStubs.SUCCESS && state == ClientState.RUNNING }
    handle {
        state = ClientState.FINISHING
        estatesResponse.addAll(ClientEstateStub.prepareSearchList(estateFilterRequest.searchString))
    }
}
