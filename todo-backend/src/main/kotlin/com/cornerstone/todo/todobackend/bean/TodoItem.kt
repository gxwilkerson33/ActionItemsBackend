package com.cornerstone.todo.todobackend.bean

import jakarta.persistence.*

@Entity
data class TodoItem(
    @Id
    @GeneratedValue
    val id: Int = -1,
    var task: String = "",
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private val user: TodoUser = TodoUser()
)