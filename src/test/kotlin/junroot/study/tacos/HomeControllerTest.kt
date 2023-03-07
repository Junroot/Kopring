package junroot.study.tacos

import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(HomeController::class)
class HomeControllerTest(@Autowired val mockMvc: MockMvc) {

	@Test
	fun testHomePage() {
		mockMvc.perform(get("/"))
			.andExpect(status().isOk)
			.andExpect(view().name("home"))
			.andExpect(content().string(
				containsString("Welcome to...")
			))
	}
}
