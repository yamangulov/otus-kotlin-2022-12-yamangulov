package ru.otus.otuskotlin.clickhouse.client.mappers.v1.exceptions

class UnknownRequestClass(clazz: Class<*>) : RuntimeException("Class $clazz cannot be mapped to ClientContext")
