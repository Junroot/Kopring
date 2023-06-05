package junroot.study.tacos.web

import junroot.study.tacos.Order
import junroot.study.tacos.User
import junroot.study.tacos.data.OrderRepository
import org.slf4j.LoggerFactory
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus
import javax.validation.Valid

@SessionAttributes("order")
@RequestMapping("/orders")
@Controller
class OrderController(
	val orderRepository: OrderRepository
) {
	companion object {
		val log = LoggerFactory.getLogger(OrderController::class.java)
	}

	@GetMapping("/current")
	fun orderForm(
		@AuthenticationPrincipal user: User,
		@ModelAttribute order: Order
	): String {
		if (order.deliveryName == null) {
			order.deliveryCity = user.fullname
		}
		if (order.deliveryStreet == null) {
			order.deliveryState = user.street
		}
		if (order.deliveryCity == null) {
			order.deliveryStreet = user.street
		}
		if (order.deliveryState == null) {
			order.deliveryState = user.state
		}
		if (order.deliveryZip == null) {
			order.deliveryZip = user.zip
		}
		return "orderForm"
	}

	@PostMapping
	fun processOrder(
		@Valid order: Order,
		errors: Errors,
		sessionStatus: SessionStatus,
		@AuthenticationPrincipal user: User
	): String {
		if (errors.hasErrors()) {
			log.info("Order has errors: $order")
			return "orderForm"
		}
		order.user = user
		orderRepository.save(order)
		sessionStatus.setComplete()
		return "redirect:/"
	}
}
