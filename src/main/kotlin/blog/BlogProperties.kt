package blog

import org.springframework.boot.context.properties.ConfigurationProperties

// TODO Use "val" instead of "lateinit var" when spring-boot#8762 will be fixed
@ConfigurationProperties("blog")
class BlogProperties {

	lateinit var title: String
	val banner = Banner()

	class Banner {
		var title: String? = null
		lateinit var content: String
	}

}
