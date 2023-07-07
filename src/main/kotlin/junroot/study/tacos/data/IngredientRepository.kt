package junroot.study.tacos.data

import junroot.study.tacos.Ingredient
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

//@RepositoryRestResource(collectionResourceRel = "ingredients")
interface IngredientRepository : CrudRepository<Ingredient, String>
