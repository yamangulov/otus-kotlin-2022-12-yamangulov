package ru.otus.otuskotlin.clickhouse.client.kafka

import ClientContext
import apiV1RequestDeserialize
import apiV1ResponseSerialize
import ru.otus.otuskotlin.clickhouse.client.api.v1.models.IRequest
import ru.otus.otuskotlin.clickhouse.client.api.v1.models.IResponse
import ru.otus.otuskotlin.clickhouse.client.mappers.v1.fromTransport
import ru.otus.otuskotlin.clickhouse.client.mappers.v1.toTransportSearch


class ConsumerStrategyV1 : ConsumerStrategy {
    override fun topics(config: AppKafkaConfig): InputOutputTopics {
        return InputOutputTopics(config.kafkaTopicInV1, config.kafkaTopicOutV1)
    }

    override fun serialize(source: ClientContext): String {
        val response: IResponse = source.toTransportSearch()
        return apiV1ResponseSerialize(response)
    }

    override fun deserialize(value: String, target: ClientContext) {
        val request: IRequest = apiV1RequestDeserialize(value)
        target.fromTransport(request)
    }
}