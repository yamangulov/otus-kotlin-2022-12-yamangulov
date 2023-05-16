package ru.otus.otuskotlin.clickhouse.client.stubs.v1

import ru.otus.otuskotlin.clickhouse.client.common.models.*

object ClientEstateStub {
    fun get(): ClientEstate = ClientEstate(
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

    fun prepareResult(block: ClientEstate.() -> Unit): ClientEstate = get().apply(block)

    fun prepareSearchList(filter: String) = listOf(
        clientEstateExample("d-666-01", filter),
        clientEstateExample("d-666-02", filter),
        clientEstateExample("d-666-03", filter),
        clientEstateExample("d-666-04", filter),
        clientEstateExample("d-666-05", filter),
        clientEstateExample("d-666-06", filter),
    )

    private fun clientEstateExample(id: String, filter: String) =
        clientEstate(get(), id = id, filter = filter)


    private fun clientEstate(base: ClientEstate, id: String, filter: String) = base.copy(
        id = ClientEstateId(id),
        town = filter
    )

}
