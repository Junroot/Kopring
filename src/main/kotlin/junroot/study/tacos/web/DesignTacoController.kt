package junroot.study.tacos.web

import junroot.study.tacos.Ingredient
import junroot.study.tacos.Taco
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/design")
@Controller
class DesignTacoController {

	companion object {
		val log = LoggerFactory.getLogger(DesignTacoController::class.java)
	}

	@GetMapping
	fun showDesignForm(model: Model): String {
		val ingredients = listOf(
			Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
			Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
			Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
			Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
			Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
			Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
			Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
			Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
			Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
			Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
		)

		val types = Ingredient.Type.values()
		for (type in types) {
			model.addAttribute(
				type.name.lowercase(),
				filterByType(ingredients, type)
			)
		}

		model.addAttribute("taco", Taco("", listOf()))

		return "design"
	}

	@PostMapping
	fun processDesign(design: Taco): String {
		log.info("Processing design: $design")
		return "redirect:/orders/current"
	}

	private fun filterByType(ingredients: List<Ingredient>, type: Ingredient.Type): List<Ingredient> {
		return ingredients.filter { it.type == type }
	}
}
