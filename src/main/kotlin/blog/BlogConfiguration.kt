package blog

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {

    @Bean
    fun databaseInitializer(userRepository: UserRepository,
                            articleRepository: ArticleRepository) = ApplicationRunner {

        val smaldini = userRepository.save(User("smaldini", "Stéphane", "Maldini"))
        articleRepository.save(Article(
                "Reactor Bismuth is out",
                "Lorem ipsum",
                "dolor sit amet",
                smaldini
        ))
        articleRepository.save(Article(
                "Reactor Aluminium has landed",
                "Lorem ipsum",
                "dolor sit amet",
                smaldini
        ))
    }
}
