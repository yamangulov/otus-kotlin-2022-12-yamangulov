import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.v1Estate() {
    route("estate") {
        post("search") {
            call.searchEstates()
        }
    }
}