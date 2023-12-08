package com.cornerstone.todo.todobackend.controller

import com.cornerstone.todo.todobackend.bean.TodoItem
import com.cornerstone.todo.todobackend.bean.User
import com.cornerstone.todo.todobackend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userRepository: UserRepository
) {
    @GetMapping("/users")
    fun getUsers():List<User>{
        return userRepository.findAll()
    }
}