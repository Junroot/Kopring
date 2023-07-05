package junroot.study.tacos.web.api

import junroot.study.tacos.Taco
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport

object TacoModelAssembler : RepresentationModelAssemblerSupport<Taco, TacoModel>(
	DesignTacoController::class.java,
	TacoModel::class.java
) {
	override fun toModel(entity: Taco): TacoModel {
		return createModelWithId(entity.id, entity)
	}

	override fun instantiateModel(entity: Taco): TacoModel {
		return TacoModel(entity)
	}
}
