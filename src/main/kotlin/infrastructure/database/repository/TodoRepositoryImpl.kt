package org.example.infrastructure.database.repository

import org.example.config.dbConnection
import org.example.domain.model.Todo
import org.example.domain.repository.TodoRepository
import org.example.presentation.form.EditTodoForm
import org.example.presentation.form.TodoForm
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.sql.SQLException

class TodoRepositoryImpl : KoinComponent, TodoRepository {
    private val dbConnection: dbConnection by inject()

    override fun getTodoLists(): MutableList<Todo> {
        val todoLists: MutableList<Todo> = mutableListOf()
        try {
            val statement = dbConnection.connection?.createStatement()
            statement?.executeQuery("select * from posts")?.let {
                var indexNum = 1
                while (it.next()) {
                    todoLists.add(
                        Todo(
                            indexNum.toLong(), it.getString(2), it.getLong(1)
                        )
                    )
                    indexNum++
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return todoLists
    }

    override fun registerTodo(todoForm: TodoForm) {
        try {
            val query = "insert into posts (title) values (?)"
            val preparedStatement =
                dbConnection.connection?.prepareStatement(query)
            preparedStatement?.setString(1, todoForm.title)
            preparedStatement?.executeUpdate()
        } catch (e: SQLException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun editTodo(editTodoForm: EditTodoForm) {
        try {
            val query = "update posts set title = (?) where post_id = (?)"
            val preparedStatement =
                dbConnection.connection?.prepareStatement(query)
            preparedStatement?.setString(1, editTodoForm.title)
            preparedStatement?.setLong(2, editTodoForm.id)
            preparedStatement?.executeUpdate()
        } catch (e: SQLException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}