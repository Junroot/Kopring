package junroot.study.tacos.web.api

import junroot.study.tacos.Ingredient
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport

object IngredientModelAssembler : RepresentationModelAssemblerSupport<Ingredient, IngredientModel>(
	IngredientController::class.java,
	IngredientModel::class.java
) {
	override fun toModel(entity: Ingredient): IngredientModel {
		return createModelWithId(entity.id, entity)
	}

	override fun instantiateModel(entity: Ingredient): IngredientModel {
		return IngredientModel(entity)
	}
}
