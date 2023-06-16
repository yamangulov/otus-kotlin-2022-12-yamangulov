package ru.otus.otuskotlin.clickhouse.client.repo.tests

import ru.otus.otuskotlin.clickhouse.client.common.models.*

abstract class BaseInitClientEstates(val op: String): IInitObjects<ClientEstate> {

    fun createInitTestModel(
        suf: String,
        type: ClientType = ClientType.FLAT,
        isnew: ClientIsNew = ClientIsNew.Y,
        category: ClientCategory = ClientCategory.A,
        ownerId: ClientUserId = ClientUserId("owner-123"),

    ) = ClientEstate(
        id = ClientEstateId("client-estate-repo-$op-$suf"),
        type = type,
        isnew = isnew,
        category = category,
        ownerId = ownerId,
    )
}