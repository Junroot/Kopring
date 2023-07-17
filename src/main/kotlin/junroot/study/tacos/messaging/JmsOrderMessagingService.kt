package junroot.study.tacos.messaging

import junroot.study.tacos.Order
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Service
import javax.jms.Destination
import javax.jms.Message

// @Service
class JmsOrderMessagingService(
	private val jmsTemplate: JmsTemplate,
	private val orderQueue: Destination
) : OrderMessagingService {

	override fun sendOrder(order: Order) {
		jmsTemplate.convertAndSend(orderQueue, order, this::addOrderSource)
	}

	private fun addOrderSource(message: Message): Message {
		message.setStringProperty("X_ORDER_SOURCE", "WEB")
		return message
	}
}
