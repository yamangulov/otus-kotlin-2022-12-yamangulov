import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.otus.otuskotlin.clickhouse.client.api.v1.models.EstateSearchRequest
import ru.otus.otuskotlin.clickhouse.client.common.ClientContext
import ru.otus.otuskotlin.clickhouse.client.mappers.v1.fromTransport
import ru.otus.otuskotlin.clickhouse.client.mappers.v1.toTransportSearch
import ru.otus.otuskotlin.clickhouse.client.process

suspend fun ApplicationCall.searchEstates() {
    val request = receive<EstateSearchRequest>()
    val context = ClientContext()
    context.fromTransport(request)
    process(context)
    respond(context.toTransportSearch())
}