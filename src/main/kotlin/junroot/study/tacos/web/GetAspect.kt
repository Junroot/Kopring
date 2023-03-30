package junroot.study.tacos.web

import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component

@Component
@Aspect
class GetAspect {

	@Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
	fun pointCut() {}

	@AfterReturning(pointcut = "pointCut()", returning = "returnValue")
	fun afterReturn(returnValue: Any) {
		println(returnValue)
	}
}
