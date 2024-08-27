package org.example.service

import org.example.domain.model.Todo
import org.example.domain.repository.TodoRepository
import org.example.presentation.form.DeleteTodoForm
import org.example.presentation.form.EditTodoForm
import org.example.presentation.form.TodoForm
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TodoService : KoinComponent {
    private val todoRepository: TodoRepository by inject()

    fun getTodoLists(): MutableList<Todo> {
        val result = todoRepository.getTodoLists()
        val todoLists: MutableList<Todo> = mutableListOf()

        result?.let {
            var indexNum = 1
            while (it.next()) {
                todoLists.add(
                    Todo(
                        postId = indexNum.toLong(),
                        title = it.getString(2),
                        dbId = it.getLong(1),
                        finishDate = it.getTimestamp(3).toLocalDateTime()
                            .toLocalDate()
                    )
                )
                indexNum++
            }
        }

        return todoLists
    }

    fun registerTodo(todoForm: TodoForm) {
        todoRepository.registerTodo(todoForm)
    }

    fun editTodo(editTodoForm: EditTodoForm) {
        todoRepository.editTodo(editTodoForm)
    }

    fun deleteTodo(deleteTodoForm: DeleteTodoForm) {
        todoRepository.deleteTodo(deleteTodoForm)
    }
}