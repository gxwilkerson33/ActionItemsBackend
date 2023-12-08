package com.cornerstone.todo.todobackend.bean

import jakarta.persistence.*

@Entity
data class TodoUser(
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    val id:Int = -1,
    var email:String = "",
    var password:String = "",
    var role:String = "",

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    var items:List<TodoItem> = listOf()
)