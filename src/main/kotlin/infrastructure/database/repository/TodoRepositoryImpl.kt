package org.example.infrastructure.database.repository

import org.example.config.dbConnection
import org.example.domain.model.Todo
import org.example.domain.repository.TodoRepository
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
                while (it.next()) {
                    todoLists.add(
                        Todo(
                            it.getLong(1), it.getString(2)
                        )
                    )
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return todoLists
    }

    override fun registerTodo(todoForm:TodoForm) {
        try {
            val query = "insert into posts (title) values (?)"
            val preparedStatement = dbConnection.connection?.prepareStatement(query)
            preparedStatement?.setString(1, todoForm.title)
            preparedStatement?.executeUpdate()
        } catch (e: SQLException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}