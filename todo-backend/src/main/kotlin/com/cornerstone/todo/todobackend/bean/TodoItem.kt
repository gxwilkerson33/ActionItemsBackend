package com.cornerstone.todo.todobackend.bean

import jakarta.persistence.*

@Entity
data class TodoItem(
    @Id
    @GeneratedValue
    val id: Int = -1,
    var task: String = "",
    private val userId: Int = -1
)