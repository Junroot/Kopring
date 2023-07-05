package junroot.study.tacos.web.api

import junroot.study.tacos.Order
import junroot.study.tacos.data.OrderRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("/orders")
@CrossOrigin(origins = ["*"])
@RestController
class OrderController(
	val orderRepository: OrderRepository
) {

	@PutMapping("/{orderId}")
	fun putOrder(@RequestBody order: Order): Order {
		return orderRepository.save(order)
	}

	@PatchMapping(path = ["/{orderId}"], consumes = ["application/json"])
	fun patchOrder(
		@PathVariable("orderId") orderId: Long?,
		@RequestBody patch: Order
	): Order? {
		val order: Order = orderRepository.findById(orderId).get()
		if (patch.deliveryName != null) {
			order.deliveryName = patch.deliveryName
		}
		if (patch.deliveryStreet != null) {
			order.deliveryStreet = patch.deliveryStreet
		}
		if (patch.deliveryCity != null) {
			order.deliveryCity = patch.deliveryCity
		}
		if (patch.deliveryState != null) {
			order.deliveryState = patch.deliveryState
		}
		if (patch.deliveryZip != null) {
			order.deliveryZip = patch.deliveryState
		}
		if (patch.ccNumber != null) {
			order.ccNumber = patch.ccNumber
		}
		if (patch.ccExpiration != null) {
			order.ccExpiration = patch.ccExpiration
		}
		if (patch.ccCVV != null) {
			order.ccCVV = patch.ccCVV
		}
		return orderRepository.save(order)
	}

	@DeleteMapping("/{orderId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun deleteOrder(@PathVariable("orderId") orderId: Long) {
		try {
			orderRepository.deleteById(orderId)
		} catch (_: EmptyResultDataAccessException) {
		}
	}
}
