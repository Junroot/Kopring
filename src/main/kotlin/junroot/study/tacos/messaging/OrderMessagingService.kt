package junroot.study.tacos.messaging

import junroot.study.tacos.Order

interface OrderMessagingService {
	fun sendOrder(order: Order)
}
