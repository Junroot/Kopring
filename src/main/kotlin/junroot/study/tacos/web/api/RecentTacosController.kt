package junroot.study.tacos.web.api

import junroot.study.tacos.data.TacoRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping

@RepositoryRestController
class RecentTacosController(
	private val tacoRepository: TacoRepository
) {

	@GetMapping("/tacos/recent", produces = ["application/hal+json"])
	fun recentTacos(): ResponseEntity<CollectionModel<TacoModel>> {
		val pageRequest = PageRequest.of(0, 12, Sort.by("createdAt").descending())
		val tacos = tacoRepository.findAll(pageRequest)
			.content
		val tacoModels = TacoModelAssembler.toCollectionModel(tacos)

		val link =
			linkTo(methodOn(RecentTacosController::class.javaObjectType).recentTacos()).withRel("recents")
		tacoModels.add(link)
		return ResponseEntity(tacoModels, HttpStatus.OK)
	}

	//	@GetMapping("/{id}")
	//	fun tacoById(@PathVariable("id") id: Long): ResponseEntity<Taco> {
	//		val taco = tacoRepository.findById(id)
	//		if (taco.isPresent) {
	//			return ResponseEntity(taco.get(), HttpStatus.OK)
	//		}
	//		return ResponseEntity(HttpStatus.NOT_FOUND)
	//	}
	//
	//	@PostMapping(consumes = ["application/json"])
	//	@ResponseStatus(HttpStatus.CREATED)
	//	fun postTaco(@RequestBody taco: Taco): Taco {
	//		return tacoRepository.save(taco)
	//	}
}
