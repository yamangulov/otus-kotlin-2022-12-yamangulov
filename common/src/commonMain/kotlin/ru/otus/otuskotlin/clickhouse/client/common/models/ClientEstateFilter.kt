package ru.otus.otuskotlin.clickhouse.client.common.models

data class ClientEstateFilter(
    var searchString: String = "",
    var ownerId: ClientUserId = ClientUserId.NONE,
)
