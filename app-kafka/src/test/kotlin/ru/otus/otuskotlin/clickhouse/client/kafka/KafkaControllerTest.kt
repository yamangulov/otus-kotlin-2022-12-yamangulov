package ru.otus.otuskotlin.clickhouse.client.kafka

import apiV1RequestSerialize
import apiV1ResponseDeserialize
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.MockConsumer
import org.apache.kafka.clients.consumer.OffsetResetStrategy
import org.apache.kafka.clients.producer.MockProducer
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringSerializer
import org.junit.Test
import ru.otus.otuskotlin.clickhouse.client.api.v1.models.*
import java.util.*
import kotlin.test.assertEquals


class KafkaControllerTest {
    @Test
    fun runKafka() {
        val consumer = MockConsumer<String, String>(OffsetResetStrategy.EARLIEST)
        val producer = MockProducer<String, String>(true, StringSerializer(), StringSerializer())
        val config = AppKafkaConfig()
        val inputTopic = config.kafkaTopicInV1
        val outputTopic = config.kafkaTopicOutV1

        val app = AppKafkaConsumer(config, listOf(ConsumerStrategyV1()), consumer = consumer, producer = producer)
        consumer.schedulePollTask {
            consumer.rebalance(Collections.singletonList(TopicPartition(inputTopic, 0)))
            consumer.addRecord(
                ConsumerRecord(
                    inputTopic,
                    PARTITION,
                    0L,
                    "test-1",
                    apiV1RequestSerialize(EstateSearchRequest(
                        requestType = "search",
                        requestId = "123",
                        debug = EstateDebug(
                            mode = EstateRequestDebugMode.STUB,
                            stub = EstateRequestDebugStubs.BAD_TABLE_NAME
                        ),
                        estateFilter = EstateSearchFilter(
                            searchString = "test search string"
                        )
                    ))
                )
            )
            app.stop()
        }

        val startOffsets: MutableMap<TopicPartition, Long> = mutableMapOf()
        val tp = TopicPartition(inputTopic, PARTITION)
        startOffsets[tp] = 0L
        consumer.updateBeginningOffsets(startOffsets)

        app.run()

        val message = producer.history().first()
        val result = apiV1ResponseDeserialize<EstateSearchResponse>(message.value())
        assertEquals(outputTopic, message.topic())
        // так как логика пока реализована с загрушками, никакого списка отфильтрованных estates не возвращается
        // и mockconsumer может получить только id запроса, данные ответа из clickhouse пока проверить нельзя
        assertEquals("123", result.requestId)
    }

    companion object {
        const val PARTITION = 0
    }
}


