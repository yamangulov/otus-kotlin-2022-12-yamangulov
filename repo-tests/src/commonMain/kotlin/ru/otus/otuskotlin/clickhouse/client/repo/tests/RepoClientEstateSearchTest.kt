package ru.otus.otuskotlin.clickhouse.client.repo.tests

import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.otus.otuskotlin.clickhouse.client.common.models.*
import ru.otus.otuskotlin.clickhouse.client.common.repo.DbClientEstateFilterRequest
import ru.otus.otuskotlin.clickhouse.client.common.repo.IClientEstateRepository
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
abstract class RepoClientEstateSearchTest {
    abstract val repo: IClientEstateRepository
    protected open val initializedObjects: List<ClientEstate> = initObjects

    @Test
    fun searchType() = runRepoTest {
        val result = repo.searchClientEstate(DbClientEstateFilterRequest(type = ClientType.TERRACED.toString()))
        assertEquals(true, result.isSuccess)
        val expected = listOf(initializedObjects[1], initializedObjects[2]).sortedBy { it.id.asString() }
        assertEquals(expected, result.data?.sortedBy { it.id.asString() })
        assertEquals(emptyList(), result.errors)
    }

    @Test
    fun searchIsNew() = runRepoTest {
        val result = repo.searchClientEstate(DbClientEstateFilterRequest(isnew = ClientIsNew.NONE.name))
        assertEquals(true, result.isSuccess)
        val expected = listOf(initializedObjects[1], initializedObjects[3]).sortedBy { it.id.asString() }
        assertEquals(expected, result.data?.sortedBy { it.id.asString() })
        assertEquals(emptyList(), result.errors)
    }

    @Test
    fun searchCategory() = runRepoTest {
        val result = repo.searchClientEstate(DbClientEstateFilterRequest(category = ClientCategory.B.toString()))
        assertEquals(true, result.isSuccess)
        val expected = listOf(initializedObjects[2], initializedObjects[4]).sortedBy { it.id.asString() }
        assertEquals(expected, result.data?.sortedBy { it.id.asString() })
        assertEquals(emptyList(), result.errors)
    }

    companion object: BaseInitClientEstates("search") {
        val searchOwnerId = ClientUserId("owner-130")
        override val initObjects: List<ClientEstate> = listOf(
            createInitTestModel("estate1"),
            createInitTestModel("estate2", ownerId = searchOwnerId,
                isnew = ClientIsNew.NONE, type = ClientType.TERRACED),
            createInitTestModel("estate3", type = ClientType.TERRACED, category = ClientCategory.B),
            createInitTestModel("estate4", isnew = ClientIsNew.NONE),
            createInitTestModel("estate5", category = ClientCategory.B),
        )
    }
}