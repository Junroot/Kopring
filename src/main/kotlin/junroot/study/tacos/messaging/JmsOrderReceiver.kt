package junroot.study.tacos.messaging

import junroot.study.tacos.Order
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.support.converter.MappingJackson2MessageConverter
import javax.jms.Destination

class JmsOrderReceiver(
	private val jmsTemplate: JmsTemplate,
	private val messageConverter: MappingJackson2MessageConverter,
	private val orderQueue: Destination
) : OrderReceiver {

	override fun receiveOrder(): Order? {
		val message = jmsTemplate.receive(orderQueue)
			?: return null
		return (messageConverter.fromMessage(message)) as Order
	}
}
