package junroot.study.tacos.web

import junroot.study.tacos.Ingredient
import junroot.study.tacos.data.IngredientRepository
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class IngredientByIdConverter(
	private val ingredientRepository: IngredientRepository
): Converter<String, Ingredient> {
	override fun convert(id: String): Ingredient? {
		return ingredientRepository.findById(id)
	}
}
