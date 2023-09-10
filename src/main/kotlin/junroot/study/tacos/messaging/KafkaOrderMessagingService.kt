package junroot.study.tacos.messaging

import junroot.study.tacos.Order
import org.springframework.kafka.core.KafkaTemplate

class KafkaOrderMessagingService(
	private val kafkaTemplate: KafkaTemplate<String, Order>
) : OrderMessagingService {

	override fun sendOrder(order: Order) {
		kafkaTemplate.sendDefault(order)
	}
}
