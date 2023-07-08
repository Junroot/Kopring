package junroot.study.tacos.data

import junroot.study.tacos.Ingredient
import org.springframework.data.repository.CrudRepository

//@RepositoryRestResource(collectionResourceRel = "ingredients")
interface IngredientRepository : CrudRepository<Ingredient, String>
