package junroot.study.tacos.data

import junroot.study.tacos.Order
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<Order, Long> {

	@Query("select o from Order o where o.deliveryCity='Seattle'")
	fun readOrdersDeliveredInSeattle(): List<Order>
}
