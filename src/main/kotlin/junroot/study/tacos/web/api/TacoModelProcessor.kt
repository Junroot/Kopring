package junroot.study.tacos.web.api

import junroot.study.tacos.Taco
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.hateoas.server.EntityLinks
import org.springframework.hateoas.server.RepresentationModelProcessor
import org.springframework.stereotype.Component

@Component
class TacoModelProcessor(
	private val links: EntityLinks
) : RepresentationModelProcessor<PagedModel<EntityModel<Taco>>> {

	override fun process(model: PagedModel<EntityModel<Taco>>): PagedModel<EntityModel<Taco>> {
		model.add(
			links.linkFor(Taco::class.java)
				.slash("recent")
				.withRel("recents")
		)
		return model
	}
}
