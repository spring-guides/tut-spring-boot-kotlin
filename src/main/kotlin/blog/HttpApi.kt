package blog

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/article")
class ArticleController(private val repository: ArticleRepository,
						private val markdownConverter: MarkdownConverter) {

	@GetMapping("/")
	fun findAll() = repository.findAllByOrderByAddedAtDesc()

	@GetMapping("/{id}")
	fun findOne(@PathVariable id: Long, @RequestParam converter: String?) = when (converter) {
		"markdown" -> repository.findById(id).map { it.copy(
				headline = markdownConverter.invoke(it.headline),
				content = markdownConverter.invoke(it.content)) }
		null -> repository.findById(id)
		else -> throw IllegalArgumentException("Only markdown converter is supported")
	}
}

@RestController
@RequestMapping("/api/user")
class UserController(private val repository: UserRepository) {

	@GetMapping("/")
	fun findAll() = repository.findAll()

	@GetMapping("/{login}")
	fun findOne(@PathVariable login: String) = repository.findById(login)
}
