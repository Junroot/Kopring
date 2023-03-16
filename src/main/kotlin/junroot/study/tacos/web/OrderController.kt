package junroot.study.tacos.web

import jakarta.validation.Valid
import junroot.study.tacos.Order
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/orders")
@Controller
class OrderController {
	companion object {
		val log = LoggerFactory.getLogger(OrderController::class.java)
	}

	@GetMapping("/current")
	fun orderForm(model: Model): String {
		model.addAttribute("order", Order())
		return "orderForm"
	}

	@PostMapping
	fun processOrder(@Valid order: Order, errors: Errors): String {
		if (errors.hasErrors()) {
			log.info("Order has errors: $order")
			return "orderForm"
		}
		log.info("Order submitted: $order")
		return "redirect:/"
	}
}
