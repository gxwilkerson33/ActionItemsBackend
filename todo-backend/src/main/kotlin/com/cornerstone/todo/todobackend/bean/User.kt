package com.cornerstone.todo.todobackend.bean

import jakarta.persistence.*

@Entity
data class User(
    @Id
    @GeneratedValue
    val id: Int = -1,
    var username: String = "",
    var password: String = "",

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "id")
    var todoItems: List<TodoItem> = listOf()
)
