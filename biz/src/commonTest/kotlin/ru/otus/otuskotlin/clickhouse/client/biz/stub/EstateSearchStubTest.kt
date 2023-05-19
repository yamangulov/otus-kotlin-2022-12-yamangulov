package ru.otus.otuskotlin.clickhouse.client.biz.stub

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import ru.otus.otuskotlin.clickhouse.client.biz.ClientEstateProcessor
import ru.otus.otuskotlin.clickhouse.client.common.ClientContext
import ru.otus.otuskotlin.clickhouse.client.common.models.*
import ru.otus.otuskotlin.clickhouse.client.common.stubs.ClientStubs
import ru.otus.otuskotlin.clickhouse.client.stubs.v1.ClientEstateStub
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

@OptIn(ExperimentalCoroutinesApi::class)
class EstateSearchStubTest {
    private val processor = ClientEstateProcessor()
    val filter = ClientEstateFilter(searchString = "sometown")

    @Test
    fun read() = runTest {

        val ctx = ClientContext(
            command = ClientCommand.SEARCH,
            state = ClientState.NONE,
            workMode = ClientWorkMode.STUB,
            stubCase = ClientStubs.SUCCESS,
            estateFilterRequest = filter,
        )
        processor.exec(ctx)
        assertTrue(ctx.estatesResponse.size > 1)
        val first = ctx.estatesResponse.firstOrNull() ?: fail("Empty response list")
        with (ClientEstateStub.get()) {
            assertEquals(type, first.type)
            assertEquals(town, first.town)
        }
    }

    @Test
    fun databaseError() = runTest {
        val ctx = ClientContext(
            command = ClientCommand.SEARCH,
            state = ClientState.NONE,
            workMode = ClientWorkMode.STUB,
            stubCase = ClientStubs.DB_ERROR,
            estateFilterRequest = filter,
        )
        processor.exec(ctx)
        assertEquals(ClientEstate(), ctx.estateResponse)
        assertEquals("internal", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun bestateNoCase() = runTest {
        val ctx = ClientContext(
            command = ClientCommand.SEARCH,
            state = ClientState.NONE,
            workMode = ClientWorkMode.STUB,
            estateFilterRequest = filter,
        )
        processor.exec(ctx)
        assertEquals(ClientEstate(), ctx.estateResponse)
        assertEquals("stub", ctx.errors.firstOrNull()?.field)
    }
}