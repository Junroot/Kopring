package junroot.study.tacos.data

import junroot.study.tacos.Order

interface OrderRepository {
	fun save(order: Order): Order
}
