package ru.otus.otuskotlin.marketplace.stubs

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.testing.*
import org.junit.Test
import ru.otus.otuskotlin.clickhouse.client.api.v1.models.*
import kotlin.test.assertEquals

class V1EstateStubApiTest {

    @Test
    fun search() = testApplication {
        val client = myClient()

        val response = client.post("/v1/estate/search") {
            val requestObj = EstateSearchRequest(
                requestId = "12345",
                estateFilter = EstateSearchFilter(),
                debug = EstateDebug(
                    mode = EstateRequestDebugMode.STUB,
                    stub = EstateRequestDebugStubs.SUCCESS
                )
            )
            contentType(ContentType.Application.Json)
            setBody(requestObj)
        }
        val responseObj = response.body<EstateSearchResponse>()
        assertEquals(200, response.status.value)
        assertEquals("d-666-01", responseObj.estates?.first()?.id)
    }


    private fun ApplicationTestBuilder.myClient() = createClient {
        install(ContentNegotiation) {
            jackson {
                disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

                enable(SerializationFeature.INDENT_OUTPUT)
                writerWithDefaultPrettyPrinter()
            }
        }
    }
}
