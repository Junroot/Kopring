package junroot.study.tacos.web

import junroot.study.tacos.Ingredient
import junroot.study.tacos.Order
import junroot.study.tacos.Taco
import junroot.study.tacos.data.IngredientRepository
import junroot.study.tacos.data.TacoRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import java.util.*
import javax.validation.Valid

@SessionAttributes("order")
@RequestMapping("/design")
@Controller
class DesignTacoController(
	private val ingredientRepository: IngredientRepository,
	private val tacoRepository: TacoRepository
) {

	companion object {
		val log = LoggerFactory.getLogger(DesignTacoController::class.java)
	}

	@ModelAttribute(name = "order")
	fun order(): Order {
		return Order()
	}

	@GetMapping
	fun showDesignForm(model: Model): String {
		val ingredients = ingredientRepository.findAll().toList()

		val types = Ingredient.Type.values()
		for (type in types) {
			model.addAttribute(
				type.name.lowercase(),
				filterByType(ingredients, type)
			)
		}

		model.addAttribute("taco", Taco(1L, Date(), "", listOf()))

		return "design"
	}

	@PostMapping
	fun processDesign(
		@Valid design: Taco,
		errors: Errors,
		@ModelAttribute("order") order: Order
	): String {
		if (errors.hasErrors()) {
			return "design"
		}
		log.info(design.toString())

		val saved = tacoRepository.save(design)
		order.addDesign(saved)

		return "redirect:/orders/current"
	}

	private fun filterByType(ingredients: List<Ingredient>, type: Ingredient.Type): List<Ingredient> {
		return ingredients.filter { it.type == type }
	}
}
