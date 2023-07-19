package junroot.study.tacos.sia5

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.integration.dsl.MessageChannels
import org.springframework.integration.file.dsl.Files
import org.springframework.integration.file.support.FileExistsMode
import java.io.File
import java.util.*

@Configuration
class FileWriterIntegrationConfig {

	@Bean
	fun fileWriterFlow(): IntegrationFlow {
		return IntegrationFlows
			.from(MessageChannels.direct("textInChannel"))
			.transform<String, String> { t -> t.uppercase() }
			.handle(
				Files.outboundAdapter(File("tmp/sia5/files"))
					.fileExistsMode(FileExistsMode.APPEND)
					.appendNewLine(true)
			)
			.get()
	}
}
