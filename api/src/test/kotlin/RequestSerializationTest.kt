import ru.otus.otuskotlin.clickhouse.client.api.v1.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class RequestSerializationTest {
    private val request = EstateSearchRequest(
        requestType = "search",
        requestId = "123",
        debug = EstateDebug(
            mode = EstateRequestDebugMode.STUB,
            stub = EstateRequestDebugStubs.BAD_TABLE_NAME
        ),
        estateFilter = EstateSearchFilter(
            searchString = "test search string"
        )
    )

    @Test
    fun serialize() {
        val json = estateV1Mapper.writeValueAsString(request)

        assertContains(json, Regex("\"searchString\":\\s*\"test search string\""))
        assertContains(json, Regex("\"mode\":\\s*\"stub\""))
        assertContains(json, Regex("\"stub\":\\s*\"badTableName\""))
        assertContains(json, Regex("\"requestType\":\\s*\"search\""))
    }

    @Test
    fun deserialize() {
        val json = estateV1Mapper.writeValueAsString(request)
        val obj = estateV1Mapper.readValue(json, IRequest::class.java) as EstateSearchRequest

        assertEquals(request, obj)
    }
}
