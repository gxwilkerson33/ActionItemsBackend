package com.cornerstone.todo.todobackend.controller

import com.cornerstone.todo.todobackend.bean.TodoItem
import com.cornerstone.todo.todobackend.repository.TodoRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TodoController(
        private val todoRepository : TodoRepository
) {

    @GetMapping("/items")
    fun getItems(@RequestParam userId:Int):List<TodoItem>{
        return todoRepository.findByUserId(userId)
    }

    @GetMapping("/allItemsDebug")
    fun getAllDebug():List<TodoItem>{
        return todoRepository.findAll()
    }

    @PostMapping("/items")
    fun addItem(@RequestBody item: TodoItem):ResponseEntity<Int>{
        val savedItem = todoRepository.save(item)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem.id)
    }

    @DeleteMapping("/items/{id}")
    fun deleteItem(@PathVariable("id") id : Int):ResponseEntity<String>{
        return try {
            todoRepository.deleteById(id)
            ResponseEntity.status(HttpStatus.OK).build()
        }catch (e:Exception){
            ResponseEntity.status(HttpStatus.OK).body(e.message)
        }
    }

}
