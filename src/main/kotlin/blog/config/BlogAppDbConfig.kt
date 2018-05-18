package blog.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 * @author Vamsi Vegi
 * @date 5/17/2018
 */

@Configuration
@EnableConfigurationProperties(BlogProperties::class)
class BlogAppDbConfig {
    // nothing here yet
}