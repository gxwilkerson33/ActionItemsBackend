package com.cornerstone.todo.todobackend.controller

import com.cornerstone.todo.todobackend.bean.ActionItem
import com.cornerstone.todo.todobackend.repository.ActionItemsRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ActionItemsController(
    private val todoRepository: ActionItemsRepository
) {

    @GetMapping("/items")
    fun getItems(@RequestParam userId: Int): List<ActionItem> {
        return todoRepository.findByUserId(userId)
    }

    @GetMapping("/allItemsDebug")
    fun getAllDebug(): List<ActionItem> {
        return todoRepository.findAll()
    }

    @PostMapping("/items")
    fun addItem(@RequestBody item: ActionItem): ResponseEntity<Int> {
        val savedItem = todoRepository.save(item)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem.id)
    }

    @DeleteMapping("/items/{id}")
    fun deleteItem(@PathVariable("id") id: Int): ResponseEntity<String> {
        return try {
            todoRepository.deleteById(id)
            ResponseEntity.status(HttpStatus.OK).build()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.OK).body(e.message)
        }
    }

    @GetMapping("/hello")
    fun hello():  ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.OK).body("Hello World")
    }

}
