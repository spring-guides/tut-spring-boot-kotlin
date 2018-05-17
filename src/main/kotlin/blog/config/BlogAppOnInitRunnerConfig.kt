package blog.config

import blog.*
import blog.service.db.ArticleRepository
import blog.service.db.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author Vamsi Vegi
 * @date 5/17/2018
 */

@Configuration
@EnableConfigurationProperties(BlogProperties::class)
class BlogAppOnInitRunnerConfig {

    @Bean
    fun databaseInitializer(userRepository: UserRepository, articleRepository: ArticleRepository) = CommandLineRunner {
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