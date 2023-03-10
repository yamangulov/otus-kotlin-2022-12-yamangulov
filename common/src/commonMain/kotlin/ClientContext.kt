import kotlinx.datetime.Instant
import models.*
import stubs.ClientStubs

data class ClientContext (
    var command: ClientCommand = ClientCommand.NONE,
    var state: ClientState = ClientState.NONE,
    val errors: MutableList<ClientError> = mutableListOf(),

    var workMode: ClientWorkMode = ClientWorkMode.PROD,
    var stubCase: ClientStubs = ClientStubs.NONE,

    var requestId: ClientRequestId = ClientRequestId.NONE,
    var timeStart: Instant = Instant.NONE,
    var estateRequest: ClientEstate = ClientEstate(),
    var estateFilterRequest: ClientEstateFilter = ClientEstateFilter(),
    var estateResponse: ClientEstate = ClientEstate(),
    var estatesResponse: MutableList<ClientEstate> = mutableListOf(),
)