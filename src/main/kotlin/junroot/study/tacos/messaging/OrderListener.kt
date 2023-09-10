package junroot.study.tacos.messaging

import junroot.study.tacos.Order
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

class OrderListener {

	companion object {
		private val logger = LoggerFactory.getLogger(OrderListener::class.java)
	}

	@KafkaListener(topics = ["tacocloud.orders.topic"], groupId = "foo")
	fun receiveOrder(order: Order, record: ConsumerRecord<String, Order>) {
		logger.info("Received from partition {} with timestamp {}", record.partition(), record.timestamp())
		logger.info(order.deliveryName)
	}
}
