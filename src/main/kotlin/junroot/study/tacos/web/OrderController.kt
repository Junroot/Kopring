package junroot.study.tacos.web

import jakarta.validation.Valid
import junroot.study.tacos.Order
import junroot.study.tacos.data.OrderRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus

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
	fun orderForm(model: Model): String {
		return "orderForm"
	}

	@PostMapping
	fun processOrder(
		@Valid order: Order,
		errors: Errors,
		sessionStatus: SessionStatus
	): String {
		if (errors.hasErrors()) {
			log.info("Order has errors: $order")
			return "orderForm"
		}
		orderRepository.save(order)
		sessionStatus.setComplete()
		return "redirect:/"
	}
}
