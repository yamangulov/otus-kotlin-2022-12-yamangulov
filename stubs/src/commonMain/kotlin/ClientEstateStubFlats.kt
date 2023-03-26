package ru.otus.otuskotlin.clickhouse.client.stubs.v1

import models.*


object ClientEstateStubFlats {
    val ESTATE1: ClientEstate
        get() = ClientEstate(
            id = ClientEstateId("666"),
            price = 1_000_000,
            date = "2023 June 23",
            postcode1 = "111111",
            postcode2 = "222222",
            type = ClientType.FLAT,
            isnew = ClientIsNew.NONE,
            duration = ClientDuration.FREEHOLED,
            addr1 = "addr1",
            addr2 = "addr2",
            street = "street",
            locality = "locality",
            town = "sometown",
            district = "district",
            county = "county",
            category = ClientCategory.A,
            ownerId = ClientUserId.NONE,
            offset = 0,
            pagesize = 1,
            permissionsClient = mutableSetOf(
                ClientEstatePermissionClient.SEARCH,
            )
        )
}
