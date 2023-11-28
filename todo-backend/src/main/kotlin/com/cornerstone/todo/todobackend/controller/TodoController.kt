package com.cornerstone.todo.todobackend.controller

import com.cornerstone.todo.todobackend.bean.TodoItem
import com.cornerstone.todo.todobackend.repository.TodoRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.web.bind.annotation.*

@RestController
class TodoController(
        private val todoRepository : TodoRepository
) {

    @GetMapping("/items")
    fun getBaseRoute():List<TodoItem>{
        return todoRepository.findAll()
    }

    @PostMapping("/items")
    fun addItem(@RequestBody item: TodoItem):TodoItem{
        return todoRepository.save(item)
    }

    @DeleteMapping("/items")
    fun deleteItem(@RequestBody item: TodoItem){
        todoRepository.delete(item)
    }

}