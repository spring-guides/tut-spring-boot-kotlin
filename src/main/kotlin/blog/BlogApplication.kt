package blog

import com.samskivert.mustache.Mustache
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableConfigurationProperties(BlogProperties::class)
class BlogApplication {

	@Bean
	fun mustacheCompiler(loader: Mustache.TemplateLoader?) =
			Mustache.compiler().escapeHTML(false).withLoader(loader)

	@Bean
	fun databaseInitializer(userRepository: UserRepository,
							articleRepository: ArticleRepository) = CommandLineRunner {
		val smaldini = User("smaldini", "Stéphane", "Maldini")
		userRepository.save(smaldini)

		articleRepository.save(Article(
				"Reactor Bismuth is out",
				"Lorem ipsum",
				"dolor **sit** amet https://projectreactor.io/",
				smaldini,
				1
		))
		articleRepository.save(Article(
				"Reactor Aluminium has landed",
				"Lorem ipsum",
				"dolor **sit** amet https://projectreactor.io/",
				smaldini,
				2
		))
	}

}

fun main(args: Array<String>) {
	runApplication<BlogApplication>(*args)
}
