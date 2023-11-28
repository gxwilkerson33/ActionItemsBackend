package com.cornerstone.todo.todobackend.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SpringSecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        http.authorizeHttpRequests { auth -> auth.anyRequest().authenticated() }
        http.httpBasic(Customizer.withDefaults())
        http.csrf().disable()

        return http.build()
    }
}

