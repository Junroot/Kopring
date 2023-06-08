package junroot.study.tacos.data

import junroot.study.tacos.Order
import junroot.study.tacos.User
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query

import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<Order, Long> {

	@Query("SELECT o FROM Order o where o.deliveryCity='Seattle'")
	fun readOrdersDeliveredInSeattle(): List<Order>

	fun findByUserOrderByPlacedAt(user: User, pageable: Pageable): List<Order>
}
