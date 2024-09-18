package com.cornerstone.todo.todobackend.bean

import jakarta.persistence.*

@Entity
data class ActionItemsUser(
    @Id
    @GeneratedValue
    @Column(name = "userId")
    val id:Int = -1,
    var email:String = "",
    var password:String = "",
    var role:String = "",
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userId")
    var items:List<ActionItem> = listOf()
)