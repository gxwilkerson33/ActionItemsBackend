package com.cornerstone.todo.todobackend.repository

import com.cornerstone.todo.todobackend.bean.TodoItem
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<TodoItem, Int>{
    fun findByUserId(userId:Int):List<TodoItem>
}