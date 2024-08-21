package org.example.infrastructure.database.repository

import org.example.config.dbConnection
import org.example.domain.repository.TodoRepository
import org.example.presentation.form.EditTodoForm
import org.example.presentation.form.TodoForm
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.sql.ResultSet
import java.sql.SQLException

class TodoRepositoryImpl : KoinComponent, TodoRepository {
    private val dbConnection: dbConnection by inject()

    override fun getTodoLists(): ResultSet? {
        try {
            val statement = dbConnection.connection?.createStatement()
            return statement?.executeQuery("select * from posts")
        } catch (e: SQLException) {
            e.printStackTrace()
            throw e
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
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
            throw e
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
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
            throw e
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

}