package ru.otus.otuskotlin.clickhouse.client.biz

import ru.otus.otuskotlin.clickhouse.client.biz.groups.operation
import ru.otus.otuskotlin.clickhouse.client.biz.groups.stubs
import ru.otus.otuskotlin.clickhouse.client.biz.workers.initStatus
import ru.otus.otuskotlin.clickhouse.client.biz.workers.stubDbError
import ru.otus.otuskotlin.clickhouse.client.biz.workers.stubNoCase
import ru.otus.otuskotlin.clickhouse.client.biz.workers.stubSearchSuccess
import ru.otus.otuskotlin.clickhouse.client.common.ClientContext
import ru.otus.otuskotlin.clickhouse.client.common.models.ClientCommand
import ru.otus.otuskotlin.clickhouse.client.cor.rootChain

class ClientEstateProcessor {
    suspend fun exec(ctx: ClientContext) = BusinessChain.exec(ctx)

    companion object {
        private val BusinessChain = rootChain<ClientContext> {
            initStatus("Инициализация статуса")

            operation("Поиск по критериям запроса в Clickhouse", ClientCommand.SEARCH) {
                stubs("Обработка стабов") {
                    stubSearchSuccess("Имитация успешной обработки")
                    stubDbError("Имитация ошибки работы с БД Clickhouse")
                    stubNoCase("Ошибка: запрошенный стаб недопустим")
                }

            }
        }.build()
    }
}
