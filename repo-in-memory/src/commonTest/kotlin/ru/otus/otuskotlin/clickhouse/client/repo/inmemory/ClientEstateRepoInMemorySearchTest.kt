package ru.otus.otuskotlin.clickhouse.client.repo.inmemory

import ru.otus.otuskotlin.clickhouse.client.common.repo.IClientEstateRepository
import ru.otus.otuskotlin.clickhouse.client.repo.tests.RepoClientEstateSearchTest

class ClientEstateRepoInMemorySearchTest : RepoClientEstateSearchTest() {
    override val repo: IClientEstateRepository = ClientEstateRepoInMemory(
        initObjects = initObjects
    )
}