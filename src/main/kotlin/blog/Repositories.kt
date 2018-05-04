package blog

import org.springframework.data.repository.CrudRepository

// @Repository is not required
interface ArticleRepository : CrudRepository<Article, Long> {
	fun findAllByOrderByAddedAtDesc(): Iterable<Article>
}

// @Repository is not required
interface UserRepository : CrudRepository<User, String>