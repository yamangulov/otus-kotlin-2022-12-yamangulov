package ru.otus.otuskotlin.clickhouse.client.mappers.v1.exceptions

import ru.otus.otuskotlin.clickhouse.client.common.models.ClientCommand
class UnknownClientCommand(command: ClientCommand) : Throwable("Wrong command $command at mapping toTransport stage")
