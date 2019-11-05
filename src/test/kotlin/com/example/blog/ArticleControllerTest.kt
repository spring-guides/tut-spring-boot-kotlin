package com.example.blog

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest
@EnableConfigurationProperties(BlogProperties::class)
class ArticleControllerTests(@Autowired val mockMvc: MockMvc) {
    @MockkBean
    lateinit var articleRepository: ArticleRepository

    @Test
    fun `List articles`() {
        val juergen = User("springjuergen", "Juergen", "Hoeller")
        val spring5Article = Article("Spring Framework 5.0 goes GA", "Dear Spring community ...", "Lorem ipsum", juergen)
        val spring43Article = Article("Spring Framework 4.3 goes GA", "Dear Spring community ...", "Lorem ipsum", juergen)
        every { articleRepository.findAllByOrderByAddedAtDesc() } returns listOf(spring5Article, spring43Article)
        mockMvc.perform(MockMvcRequestBuilders.get("/api/article/").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("\$.[0].author.login").value(juergen.login))
                .andExpect(MockMvcResultMatchers.jsonPath("\$.[0].slug").value(spring5Article.slug))
                .andExpect(MockMvcResultMatchers.jsonPath("\$.[1].author.login").value(juergen.login))
                .andExpect(MockMvcResultMatchers.jsonPath("\$.[1].slug").value(spring43Article.slug))
    }
}