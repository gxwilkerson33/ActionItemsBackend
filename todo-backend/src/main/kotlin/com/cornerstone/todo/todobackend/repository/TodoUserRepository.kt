package com.cornerstone.todo.todobackend.repository

import com.cornerstone.todo.todobackend.bean.ActionItemsUser
import org.springframework.data.jpa.repository.JpaRepository

interface ActionItemsUserRepository : JpaRepository<ActionItemsUser, Int> {
    fun findByEmail(email: String): List<ActionItemsUser>

}