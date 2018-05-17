package blog.config

import com.samskivert.mustache.Mustache
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author Vamsi Vegi
 * @date 5/17/2018
 */

@Configuration
@EnableConfigurationProperties(BlogProperties::class)
class BlogAppWebConfig {
    @Bean
    fun mustacheCompiler(loader: Mustache.TemplateLoader?) =
            Mustache.compiler().escapeHTML(false).withLoader(loader)

}