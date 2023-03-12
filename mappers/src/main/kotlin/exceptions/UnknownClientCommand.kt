package ru.otus.otuskotlin.clickhouse.client.mappers.v1.exceptions

import models.ClientCommand
class UnknownClientCommand(command: ClientCommand) : Throwable("Wrong command $command at mapping toTransport stage")
