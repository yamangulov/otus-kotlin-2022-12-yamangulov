package ru.otus.otuskotlin.clickhouse.client.cor

/**
 * Блок кода, который обрабатывает контекст. Имеет имя и описание
 */
interface ICorExec<T> {
    val title: String
    val description: String
    suspend fun exec(context: T)
}
