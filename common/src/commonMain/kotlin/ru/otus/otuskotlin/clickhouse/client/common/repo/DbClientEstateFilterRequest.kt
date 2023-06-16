package ru.otus.otuskotlin.clickhouse.client.common.repo

data class DbClientEstateFilterRequest(
    val type: String = "",
    val isnew: String = "",
    val category: String = "",
)
