package junroot.study.tacos.data

import junroot.study.tacos.Order
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<Order, Long>
