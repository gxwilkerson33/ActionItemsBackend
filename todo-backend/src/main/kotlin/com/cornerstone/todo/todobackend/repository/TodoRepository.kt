package com.cornerstone.todo.todobackend.repository

import com.cornerstone.todo.todobackend.bean.ActionItem
import org.springframework.data.jpa.repository.JpaRepository

interface ActionItemsRepository : JpaRepository<ActionItem, Int>{
    fun findByUserId(userId:Int):List<ActionItem>
}