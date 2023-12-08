package com.cornerstone.todo.todobackend.bean

import jakarta.persistence.*

@Entity
data class TodoItem(
    @Id
    @GeneratedValue
    val id: Int = -1,
//        todo: convert this to Task object
    var task: String = "",
    @ManyToOne
    @JoinColumn(name = "id" )
    var user: User = User()
)