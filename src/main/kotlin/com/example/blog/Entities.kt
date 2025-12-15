package com.example.blog

import java.time.LocalDateTime
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.jdbc.core.mapping.AggregateReference

@Table("article")
data class Article(
    val title: String,
    val headline: String,
    val content: String,
    @Column("author_id")
    val author: AggregateReference<User, Long>,
    val slug: String = title.toSlug(),
    val addedAt: LocalDateTime = LocalDateTime.now(),
    @Id val id: Long? = null
)

@Table("users")
data class User(
    val login: String,
    val firstname: String,
    val lastname: String,
    val description: String? = null,
    @Id val id: Long? = null
)