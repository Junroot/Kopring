package junroot.study.tacos

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class AsyncLogger {

	@Async
	fun log() {
		Thread.sleep(5000)
		println("[${this::class}] ${Thread.currentThread().id}")
	}
}
