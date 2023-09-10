package junroot.study.tacos.web.api

import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.NotEmpty

@RestController
class TestController(
	private val testService: TestService
) {

	@PostMapping(value = ["/test"])
	fun test(@RequestBody body: TestBody) {
//		println(body.name)
		testService.innerTest(body)
	}
}

@Validated
@Service
class TestService {

	fun innerTest(@Valid body:TestBody) {
		println(body.name)
	}
}

data class TestBody(
	@field:NotEmpty
	val name: String
)
