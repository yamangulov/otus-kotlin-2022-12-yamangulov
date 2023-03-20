package ru.otus.otuskotlin.clickhouse.client.stubs.v1

import models.ClientEstate
import models.ClientEstateId
import ru.otus.otuskotlin.clickhouse.client.stubs.v1.ClientEstateStubFlats.ESTATE1

object ClientEstateStub {
    fun get(): ClientEstate = ESTATE1.copy()

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
        clientEstate(ESTATE1, id = id, filter = filter)


    private fun clientEstate(base: ClientEstate, id: String, filter: String) = base.copy(
        id = ClientEstateId(id),
        town = filter
    )

}
