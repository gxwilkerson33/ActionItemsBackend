package com.cornerstone.todo.todobackend.controller

import UserAlreadyExistAuthenticationException
import com.cornerstone.todo.todobackend.bean.TodoUser
import com.cornerstone.todo.todobackend.repository.TodoUserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(
    private val todoUserRepository: TodoUserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @PostMapping("/register")
    fun registerUser(@RequestBody user: TodoUser): ResponseEntity<String> {
        return try {
            val userAlreadyExists = todoUserRepository.findByEmail(user.email).isNotEmpty()
            if (userAlreadyExists) {
                throw UserAlreadyExistAuthenticationException()
            }
            todoUserRepository.save(user.apply { password = passwordEncoder.encode(this.password) })
            ResponseEntity.status(HttpStatus.CREATED).body("User created successfully")
        } catch (e:UserAlreadyExistAuthenticationException){
            ResponseEntity.status(HttpStatus.CONFLICT).body(e.message ?: "Exception occurred during registration.")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase)
        }

    }

}


