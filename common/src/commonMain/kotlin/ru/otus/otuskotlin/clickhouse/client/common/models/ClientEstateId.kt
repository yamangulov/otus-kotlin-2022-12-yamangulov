package ru.otus.otuskotlin.clickhouse.client.common.models

@JvmInline
value class ClientEstateId(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = ClientEstateId("")
    }
}
