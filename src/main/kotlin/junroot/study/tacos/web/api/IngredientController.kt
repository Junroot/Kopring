package junroot.study.tacos.web.api

import junroot.study.tacos.Ingredient
import junroot.study.tacos.data.IngredientRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/ingredients")
@CrossOrigin(origins = ["*"])
@RestController
class IngredientController(
	private val ingredientRepository: IngredientRepository
) {

	@GetMapping("/{id}")
	fun getIngredient(id: String): Ingredient {
		return ingredientRepository.findByIdOrNull(id) ?: throw IllegalArgumentException()
	}
}
