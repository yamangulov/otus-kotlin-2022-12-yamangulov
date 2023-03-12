package ru.otus.otuskotlin.clickhouse.client.mappers.v1

import ClientContext
import models.*
import ru.otus.otuskotlin.clickhouse.client.api.v1.models.*
import ru.otus.otuskotlin.clickhouse.client.mappers.v1.exceptions.UnknownClientCommand

fun ClientContext.toTransportEstate(): IResponse = when (val cmd = command) {
    ClientCommand.SEARCH -> toTransportSearch()
    ClientCommand.NONE -> throw UnknownClientCommand(cmd)
}

fun ClientContext.toTransportSearch() = EstateSearchResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == ClientState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    estates = estatesResponse.toTransportEstate()
)

fun List<ClientEstate>.toTransportEstate(): List<EstateResponseObject>? = this
    .map { it.toTransportEstate() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun ClientEstate.toTransportEstate(): EstateResponseObject = EstateResponseObject(
    id = id.takeIf { it != models.ClientEstateId.NONE }?.asString(),
    price = price,
    date = date.takeIf { it.isNotBlank() },
    postcode1 = postcode1.takeIf { it.isNotBlank() },
    postcode2 = postcode2.takeIf { it.isNotBlank() },
    type = type.toTransportEstate(),
    isnew = isnew.toTransportEstate(),
    duration = duration.toTransportEstate(),
    addr1 = addr1.takeIf { it.isNotBlank() },
    addr2 = addr2.takeIf { it.isNotBlank() },
    street = street.takeIf { it.isNotBlank() },
    locality = locality.takeIf { it.isNotBlank() },
    town = town.takeIf { it.isNotBlank() },
    district = district.takeIf { it.isNotBlank() },
    county = county.takeIf { it.isNotBlank() },
    category = category.toTransportEstate(),
    ownerId = ownerId.takeIf { it != ClientUserId.NONE }?.asString(),
    permissions = permissionsClient.toTransportEstate(),
    offset = offset,
    pagesize = pagesize
)

private fun Set<ClientEstatePermissionClient>.toTransportEstate(): Set<EstatePermissions>? = this
    .map { it.toTransportEstate() }
    .toSet()
    .takeIf { it.isNotEmpty() }

private fun ClientEstatePermissionClient.toTransportEstate() = when (this) {
    ClientEstatePermissionClient.SEARCH -> EstatePermissions.SEARCH
}

private fun List<ClientError>.toTransportErrors(): List<Error>? = this
    .map { it.toTransportEstate() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun ClientError.toTransportEstate() = Error(
    code = code.takeIf { it.isNotBlank() },
    group = group.takeIf { it.isNotBlank() },
    field = field.takeIf { it.isNotBlank() },
    message = message.takeIf { it.isNotBlank() },
)

private fun ClientType.toTransportEstate() = when (this) {
    ClientType.TERRACED -> Type.TERRACED
    ClientType.DETACHED -> Type.DETACHED
    ClientType.SEMIDETACHED -> Type.SEMIDETACHED
    ClientType.FLAT -> Type.FLAT
    ClientType.OTHER -> Type.OTHER
    ClientType.NONE -> null
}

private fun ClientIsNew.toTransportEstate() = when (this) {
    ClientIsNew.Y -> IsNew.Y
    ClientIsNew.NONE -> null
}

private fun ClientDuration.toTransportEstate() = when (this) {
    ClientDuration.FREEHOLE -> Duration.FREEHOLD
    ClientDuration.LEASEHOLD -> Duration.LEASEHOLD
    ClientDuration.UNKNOWN -> Duration.UNKNOWN
}

private fun ClientCategory.toTransportEstate() = when (this) {
    ClientCategory.B -> Category.B
    ClientCategory.A -> null
}