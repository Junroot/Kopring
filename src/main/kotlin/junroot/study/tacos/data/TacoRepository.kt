package junroot.study.tacos.data

import junroot.study.tacos.Taco
import org.springframework.data.repository.CrudRepository

interface TacoRepository : CrudRepository<Taco, Long>
