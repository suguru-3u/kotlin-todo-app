package org.example.domain.repository

import org.example.domain.model.Todo

interface TodoRepository {
    fun getTodoLists(): MutableList<Todo>
}