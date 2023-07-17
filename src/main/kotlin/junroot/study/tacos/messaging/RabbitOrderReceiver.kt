package junroot.study.tacos.messaging

import junroot.study.tacos.Order
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Component

//@Component
class RabbitOrderReceiver(
	private val rabbitTemplate: RabbitTemplate,
	private val messageConverter: MessageConverter
) {
	fun receiveOrder(): Order? {
		return rabbitTemplate.receiveAndConvert(
			"tacocloud.order.queue",
			object : ParameterizedTypeReference<Order>() {}
		)
	}
}
