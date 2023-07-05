package junroot.study.tacos.web.api

import junroot.study.tacos.Taco
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.core.Relation
import java.util.Date

@Relation(value = "taco", collectionRelation = "tacos")
class TacoModel private constructor(
	val name: String,
	val createdAt: Date,
	val ingredients: CollectionModel<IngredientModel>
): RepresentationModel<TacoModel>() {

	constructor(taco: Taco): this(taco.name, taco.createdAt, IngredientModelAssembler.toCollectionModel(taco.ingredients))
}
