package com.cornerstone.todo.todobackend.controller

import UserAlreadyExistAuthenticationException
import com.cornerstone.todo.todobackend.bean.ActionItem
import com.cornerstone.todo.todobackend.bean.ActionItemsUser
import com.cornerstone.todo.todobackend.repository.ActionItemsUserRepository
import jakarta.websocket.Decoder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController(
    private val todoUserRepository: ActionItemsUserRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    @PostMapping("/register")
    fun registerUser(@RequestBody user: ActionItemsUser): ResponseEntity<String> {
        return try {
            val userAlreadyExists = todoUserRepository.findByEmail(user.email).isNotEmpty()
            if (userAlreadyExists) {
                throw UserAlreadyExistAuthenticationException()
            }
            todoUserRepository.save(user.apply { password = passwordEncoder.encode(this.password) })
            ResponseEntity.status(HttpStatus.CREATED).body("User created successfully")
        } catch (e: UserAlreadyExistAuthenticationException) {
            ResponseEntity.status(HttpStatus.CONFLICT).body(e.message ?: "Exception occurred during registration.")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase)
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody user: ActionItemsUser): ResponseEntity<String> {
        return try {
            val userWithEmail = todoUserRepository.findByEmail(user.email)
            if (userWithEmail.isNotEmpty()) {
                val passwordMatches =
                    passwordEncoder.encode(userWithEmail[0].password) == passwordEncoder.encode(user.password)
                if (passwordMatches) {
//                    TODO generate and pass real token for later autentication
                    ResponseEntity.status(HttpStatus.OK).body("exampletoken")
                }
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect credentials")
            } else {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user with the email exxists")
            }
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase)
        }
    }

    @GetMapping("/allUsersDebug")
    fun registerUser(): ResponseEntity<List<ActionItemsUser>> {
        return ResponseEntity.status(HttpStatus.OK).body(todoUserRepository.findAll())
    }

}


