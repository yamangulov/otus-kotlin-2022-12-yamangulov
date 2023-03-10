import models.*
import org.junit.Test
import ru.otus.otuskotlin.clickhouse.client.api.v1.models.*
import ru.otus.otuskotlin.clickhouse.client.mappers.v1.fromTransport
import ru.otus.otuskotlin.clickhouse.client.mappers.v1.toTransportEstate
import stubs.ClientStubs
import kotlin.test.assertEquals

class MapperTest {
    @Test
    fun fromTransport() {
        val req = EstateSearchRequest(
            requestType = "search",
            requestId = "1234",
            debug = EstateDebug(
                mode = EstateRequestDebugMode.STUB,
                stub = EstateRequestDebugStubs.SUCCESS,
            ),
            estateFilter = EstateSearchFilter(
                searchString = "exampleSearchString",
            ),
        )

        val context = ClientContext()
        context.fromTransport(req)

        assertEquals(ClientStubs.SUCCESS, context.stubCase)
        assertEquals(ClientWorkMode.STUB, context.workMode)
        assertEquals("", context.estateRequest.district)
        assertEquals(ClientCategory.A, context.estateRequest.category)

    }

    @Test
    fun toTransport() {
        val context = ClientContext(
            command = ClientCommand.SEARCH,
            state = ClientState.RUNNING,
            errors = mutableListOf(
                ClientError(
                    code = "err",
                    group = "request",
                    field = "title",
                    message = "wrong title",
                )
            ),
            workMode = ClientWorkMode.PROD,
            stubCase = ClientStubs.NONE,
            requestId = ClientRequestId("1234"),
            estateResponse = ClientEstate(
                district = "district",
                category = ClientCategory.B,
            ),


        )

        val req = context.toTransportEstate() as EstateSearchResponse

        assertEquals("1234", req.requestId)
        assertEquals(null, req.estates?.get(0)?.district)
        assertEquals(null, req.estates?.get(0)?.category)
        assertEquals(1, req.errors?.size)
        assertEquals("err", req.errors?.firstOrNull()?.code)
        assertEquals("request", req.errors?.firstOrNull()?.group)
        assertEquals("title", req.errors?.firstOrNull()?.field)
        assertEquals("wrong title", req.errors?.firstOrNull()?.message)
    }
}
