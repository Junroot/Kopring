package junroot.study.tacos.web.api

import com.fasterxml.jackson.annotation.JsonCreator
import junroot.study.tacos.Taco
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.core.Relation
import java.util.*

@Relation(value = "taco", collectionRelation = "tacos")
class TacoModel private constructor(
	val name: String,
	val createdAt: Date,
	val ingredients: List<IngredientModel>
) : RepresentationModel<TacoModel>() {
	constructor(taco: Taco) : this(
		taco.name,
		taco.createdAt,
		IngredientModelAssembler.toCollectionModel(taco.ingredients).toList()
	)
}
