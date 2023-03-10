import ru.otus.otuskotlin.clickhouse.client.api.v1.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ResponseSerializationTest {
    private val response = EstateSearchResponse(
        responseType = "search",
        requestId = "123",
        result = ResponseResult.SUCCESS,
        errors = null,
        estates = listOf(
            EstateResponseObject(
                id = "1",
                filter = EstateSearchFilter("test search string"),
                offset = null,
                pagesize = 1,
                ownerId = null,
                permissions = null
            )
        )
    )

    @Test
    fun serialize() {
        val json = estateV1Mapper.writeValueAsString(response)

        assertContains(json, Regex("\"searchString\":\\s*\"test search string\""))
        assertContains(json, Regex("\"responseType\":\\s*\"search\""))
    }

    @Test
    fun deserialize() {
        val json = estateV1Mapper.writeValueAsString(response)
        val obj = estateV1Mapper.readValue(json, IResponse::class.java) as EstateSearchResponse

        assertEquals(response, obj)
    }
}
