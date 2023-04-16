package ru.otus.otuskotlin.clienthouse.client.biz

import ClientContext
import ru.otus.otuskotlin.clickhouse.client.stubs.v1.ClientEstateStub

class ClientEstateProcessor {
    suspend fun exec(ctx: ClientContext) {
        ctx.estateResponse = ClientEstateStub.get()
    }
}
