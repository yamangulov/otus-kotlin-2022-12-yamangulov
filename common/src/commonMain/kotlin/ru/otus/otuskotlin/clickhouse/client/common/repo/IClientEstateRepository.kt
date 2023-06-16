package ru.otus.otuskotlin.clickhouse.client.common.repo

interface IClientEstateRepository {
    suspend fun searchClientEstate(rq: DbClientEstateFilterRequest): DbClientEstatesResponse

    companion object {
        val NONE = object : IClientEstateRepository {
            override suspend fun searchClientEstate(rq: DbClientEstateFilterRequest): DbClientEstatesResponse {
                TODO("Not yet implemented")
            }
        }
    }
}