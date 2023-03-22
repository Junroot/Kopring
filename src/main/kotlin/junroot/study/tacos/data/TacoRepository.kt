package junroot.study.tacos.data

import junroot.study.tacos.Taco

interface TacoRepository {
	fun save(design: Taco): Taco
}
