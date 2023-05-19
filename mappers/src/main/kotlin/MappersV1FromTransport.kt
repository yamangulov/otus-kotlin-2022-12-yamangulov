package ru.otus.otuskotlin.clickhouse.client.mappers.v1

import ru.otus.otuskotlin.clickhouse.client.api.v1.models.*
import ru.otus.otuskotlin.clickhouse.client.common.ClientContext
import ru.otus.otuskotlin.clickhouse.client.common.models.*
import ru.otus.otuskotlin.clickhouse.client.common.stubs.ClientStubs
import ru.otus.otuskotlin.clickhouse.client.mappers.v1.exceptions.UnknownRequestClass

fun ClientContext.fromTransport(request: IRequest) = when (request) {
    is EstateSearchRequest -> fromTransport(request)
    else -> throw UnknownRequestClass(request.javaClass)
}

private fun String?.toEstateId() = this?.let { ClientEstateId(it) } ?: ClientEstateId.NONE
private fun String?.toEstateWithId() = ClientEstate(id = this.toEstateId())
private fun IRequest?.requestId() = this?.requestId?.let { ClientRequestId(it) } ?: ClientRequestId.NONE

private fun EstateDebug?.transportToWorkMode(): ClientWorkMode = when (this?.mode) {
    EstateRequestDebugMode.PROD -> ClientWorkMode.PROD
    EstateRequestDebugMode.TEST -> ClientWorkMode.TEST
    EstateRequestDebugMode.STUB -> ClientWorkMode.STUB
    null -> ClientWorkMode.PROD
}

private fun EstateDebug?.transportToStubCase(): ClientStubs = when (this?.stub) {
    EstateRequestDebugStubs.SUCCESS -> ClientStubs.SUCCESS
    EstateRequestDebugStubs.NOT_FOUND -> ClientStubs.NOT_FOUND
    EstateRequestDebugStubs.BAD_TABLE_NAME -> ClientStubs.BAD_TABLE_NAME
    EstateRequestDebugStubs.BAD_OFFSET -> ClientStubs.BAD_OFFSET
    EstateRequestDebugStubs.BAD_PAGESIZE -> ClientStubs.BAD_PAGESIZE
    EstateRequestDebugStubs.BAD_SEARCH_STRING -> ClientStubs.BAD_SEARCH_STRING
    null -> ClientStubs.NONE
}
fun ClientContext.fromTransport(request: EstateSearchRequest) {
    command = ClientCommand.SEARCH
    requestId = request.requestId()
    estateFilterRequest = request.estateFilter.toInternal()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

private fun EstateSearchFilter?.toInternal(): ClientEstateFilter = ClientEstateFilter(
    searchString = this?.searchString ?: ""
)


