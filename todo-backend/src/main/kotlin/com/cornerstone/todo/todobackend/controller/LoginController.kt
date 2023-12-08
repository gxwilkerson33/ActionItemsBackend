package com.cornerstone.todo.todobackend.controller

import com.cornerstone.todo.todobackend.bean.TodoUser
import com.cornerstone.todo.todobackend.repository.TodoUserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(
    private val todoUserRepository: TodoUserRepository
) {

    @PostMapping("/register")
    fun registerUser(@RequestBody user: TodoUser): ResponseEntity<String> {
        return try {
            todoUserRepository.save(user)
            ResponseEntity.status(HttpStatus.CREATED).body("User creates succesfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred during registration. ")
        }

    }
}