package junroot.study.tacos.messaging

import junroot.study.tacos.Order
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

//@Service
class RabbitOrderMessagingService(
	private val rabbitTemplate: RabbitTemplate
) : OrderMessagingService {
	override fun sendOrder(order: Order) {
		rabbitTemplate.convertAndSend("tacocloud.order.queue", order) { message ->
			val props = message.messageProperties
			props.setHeader("X_ORDER_SOURCE", "WEB")
			message
		}
	}
}
