package com.cornerstone.todo.todobackend.bean

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class TodoItem(
        @Id
        @GeneratedValue
        val id: Int = -1,
//        todo: convert this to Task object
        var task: String = ""
)