package blog

import org.commonmark.ext.autolink.AutolinkExtension
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer

import org.springframework.stereotype.Service

@Service
class MarkdownConverter : (String?) -> String {

	private val parser = Parser.builder().extensions(listOf(AutolinkExtension.create())).build()
	private val renderer = HtmlRenderer.builder().build()

	override fun invoke(input: String?): String {
		if (input == null || input == "") {
			return ""
		}
		return renderer.render(parser.parse(input))
	}
}
