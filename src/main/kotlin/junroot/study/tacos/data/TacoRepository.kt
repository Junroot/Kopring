package junroot.study.tacos.data

import junroot.study.tacos.Taco
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface TacoRepository : ReactiveCrudRepository<Taco, Long>
