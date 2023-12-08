package com.cornerstone.todo.todobackend.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SpringSecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        http.authorizeHttpRequests { auth -> auth.requestMatchers("/items","/items/*").authenticated() }
            .authorizeHttpRequests { auth -> auth.requestMatchers("/register").permitAll() }
            .httpBasic(Customizer.withDefaults())
        .csrf().disable()

        return http.build()
    }

    @Bean
    fun noopPasswordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }
}

