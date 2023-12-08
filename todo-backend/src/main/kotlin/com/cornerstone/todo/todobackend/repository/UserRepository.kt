package com.cornerstone.todo.todobackend.repository

import com.cornerstone.todo.todobackend.bean.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User,Int> {
}