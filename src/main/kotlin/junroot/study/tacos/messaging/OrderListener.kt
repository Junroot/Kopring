package junroot.study.tacos.messaging

import junroot.study.tacos.Order
import org.slf4j.LoggerFactory
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component

@Component
class OrderListener {

	companion object {
		private val logger = LoggerFactory.getLogger(OrderListener::class.java)
	}

	@JmsListener(destination = "tacocloud.order.queue")
	fun receiveOrder(order: Order) {
		logger.info(order.deliveryName)
	}
}
