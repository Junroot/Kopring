package junroot.study.tacos.messaging

import junroot.study.tacos.Order

interface OrderReceiver {
	fun receiveOrder(): Order?
}
