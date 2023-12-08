package com.cornerstone.todo.todobackend.repository

import com.cornerstone.todo.todobackend.bean.TodoUser
import org.springframework.data.jpa.repository.JpaRepository

interface TodoUserRepository : JpaRepository<TodoUser, Int> {
    fun findByEmail(email: String): List<TodoUser>

}