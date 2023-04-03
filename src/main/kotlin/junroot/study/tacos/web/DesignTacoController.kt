package junroot.study.tacos.web

import junroot.study.tacos.Ingredient
import junroot.study.tacos.Order
import junroot.study.tacos.Taco
import junroot.study.tacos.data.IngredientRepository
import junroot.study.tacos.data.TacoRepository
import org.slf4j.LoggerFactory
import org.springframework.integration.support.locks.ExpirableLockRegistry
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.concurrent.locks.Lock
import javax.validation.Valid

@SessionAttributes("order")
@RequestMapping("/design")
@Controller
class DesignTacoController(
	private val ingredientRepository: IngredientRepository,
	private val tacoRepository: TacoRepository,
	private val lockRegistry: ExpirableLockRegistry
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
		val lock = try {
			lockRegistry.obtain("lock#3")
		} catch (e: Exception) {
			println(String.format("Unable to obtain lock: lock#3"))
			null
		}
		try {
			val success = lock?.tryLock() ?: false
			if (!success) {
				throw IllegalStateException("lock 걸려 있음")
			}
			val ingredients = ingredientRepository.findAll().toList()

			val types = Ingredient.Type.values()
			for (type in types) {
				model.addAttribute(
					type.name.lowercase(),
					filterByType(ingredients, type)
				)
			}
		} finally {
			lock?.unlock()
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
