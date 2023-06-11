package junroot.study.tacos.data

import junroot.study.tacos.Taco
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface TacoRepository : PagingAndSortingRepository<Taco, Long>
