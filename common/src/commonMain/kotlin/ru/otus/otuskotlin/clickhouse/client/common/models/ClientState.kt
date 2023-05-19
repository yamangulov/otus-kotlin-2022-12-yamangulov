package ru.otus.otuskotlin.clickhouse.client.common.models

enum class ClientState {
    NONE,
    RUNNING,
    FAILING,
    FINISHING,
}
