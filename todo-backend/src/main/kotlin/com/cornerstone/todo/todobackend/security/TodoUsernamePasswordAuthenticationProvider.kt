package com.cornerstone.todo.todobackend.security

import com.cornerstone.todo.todobackend.repository.ActionItemsUserRepository
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class ActionItemsUsernamePasswordAuthenticationProvider(
    private val todoUserRepository: ActionItemsUserRepository,
    private val passwordEncoder: PasswordEncoder
) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication?): Authentication {
        val users = authentication?.name?.let { todoUserRepository.findByEmail(it) }
        val pwd = authentication?.credentials.toString()

        if (users?.isNotEmpty() == true) {
            if (passwordEncoder.matches(pwd, users[0].password)) {
                return UsernamePasswordAuthenticationToken(
                    authentication.name, pwd, listOf(SimpleGrantedAuthority(users[0].role))
                )
            } else {
                throw BadCredentialsException("Invalid password")
            }
        } else {
            throw BadCredentialsException("Invalid username")
        }

    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication?.let { UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(it) } ?: false
    }
}