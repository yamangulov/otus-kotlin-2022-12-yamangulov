package ru.otus.otuskotlin.clickhouse.client.repo.inmemory

import io.github.reactivecircus.cache4k.Cache
import ru.otus.otuskotlin.clickhouse.client.common.models.ClientError
import ru.otus.otuskotlin.clickhouse.client.common.models.ClientEstate
import ru.otus.otuskotlin.clickhouse.client.common.repo.DbClientEstateFilterRequest
import ru.otus.otuskotlin.clickhouse.client.common.repo.DbClientEstatesResponse
import ru.otus.otuskotlin.clickhouse.client.common.repo.IClientEstateRepository
import ru.otus.otuskotlin.clickhouse.client.repo.inmemory.model.ClientEstateEntity
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

class ClientEstateRepoInMemory(
    initObjects: List<ClientEstate> = emptyList(),
    ttl: Duration = 2.minutes,
) : IClientEstateRepository {

    private val cache = Cache.Builder<String, ClientEstateEntity>()
        .expireAfterWrite(ttl)
        .build()

    init {
        initObjects.forEach {
            save(it)
        }
    }

    private fun save(clientEstate: ClientEstate) {
        val entity = ClientEstateEntity(clientEstate)
        if (entity.id == null) {
            return
        }
        cache.put(entity.id, entity)
    }

    /**
     * Поиск объектов недвижимости по фильтру
     * Если в фильтре не установлен какой-либо из параметров - по нему фильтрация не идет
     */
    override suspend fun searchClientEstate(rq: DbClientEstateFilterRequest): DbClientEstatesResponse {
        val result = cache.asMap().asSequence()
            .filter { entry ->
                rq.type.takeIf { it.isNotBlank() }?.let {
                    it == entry.value.type
                } ?: true
            }
            .filter { entry ->
                rq.isnew.takeIf { it.isNotBlank() }?.let {
                    it == entry.value.isnew
                } ?: true
            }
            .filter { entry ->
                rq.category.takeIf { it.isNotBlank() }?.let {
                    it == entry.value.category
                } ?: true
            }
            .map { it.value.toInternal() }
            .toList()
        return DbClientEstatesResponse(
            data = result,
            isSuccess = true
        )
    }

    companion object {
        val resultErrorNotFound = DbClientEstatesResponse(
            isSuccess = false,
            data = null,
            errors = listOf(
                ClientError(
                    code = "not-found",
                    field = "id",
                    message = "Not Found"
                )
            )
        )
    }

}