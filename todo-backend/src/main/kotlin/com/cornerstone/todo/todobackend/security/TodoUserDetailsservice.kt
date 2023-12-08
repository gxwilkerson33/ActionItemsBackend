package com.cornerstone.todo.todobackend.security

import com.cornerstone.todo.todobackend.bean.TodoUser
import com.cornerstone.todo.todobackend.repository.TodoUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class TodoUserDetailsService : UserDetailsService {

    @Autowired
    private lateinit var todoUserRepository: TodoUserRepository
    override fun loadUserByUsername(username: String?): UserDetails {
        val userName: String?
        val password: String?
        val authorities: MutableList<GrantedAuthority> = mutableListOf()
        if (username.isNullOrBlank()){
            throw UsernameNotFoundException("Username is null or empty")
        }
        var users: List<TodoUser> = mutableListOf()
        try {
            users = todoUserRepository.findByEmail(username)
        }catch (e:Exception){
            println(e)
        }

        if (users.isEmpty()){
            throw UsernameNotFoundException("UserDetails not found for user: $username")
        }else{
            userName = users[0].email
            password = users[0].password
            authorities.add(SimpleGrantedAuthority(users[0].role))
        }
        return User(userName,password,authorities)
    }
}