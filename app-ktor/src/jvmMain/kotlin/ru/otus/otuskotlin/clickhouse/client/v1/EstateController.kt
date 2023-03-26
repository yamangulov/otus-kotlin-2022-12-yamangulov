import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.otus.otuskotlin.clickhouse.client.api.v1.models.EstateSearchRequest
import ru.otus.otuskotlin.clickhouse.client.mappers.v1.fromTransport
import ru.otus.otuskotlin.clickhouse.client.mappers.v1.toTransportSearch
import ru.otus.otuskotlin.clickhouse.client.stubs.v1.ClientEstateStub

suspend fun ApplicationCall.searchEstates() {
    val request = receive<EstateSearchRequest>()
    val context = ClientContext()
    context.fromTransport(request)
    context.estatesResponse.addAll(ClientEstateStub.prepareSearchList("sometown"))
    respond(context.toTransportSearch())
}