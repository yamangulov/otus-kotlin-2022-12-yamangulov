package ru.otus.otuskotlin.clickhouse.client

import ru.otus.otuskotlin.clickhouse.client.biz.ClientEstateProcessor
import ru.otus.otuskotlin.clickhouse.client.common.ClientContext

private val processor = ClientEstateProcessor()
suspend fun process(ctx: ClientContext) = processor.exec(ctx)

