package org.example.infrastructure.database.repository

import org.example.config.dbConnection
import org.example.domain.repository.TodoRepository
import org.example.presentation.form.DeleteTodoForm
import org.example.presentation.form.EditTodoForm
import org.example.presentation.form.TodoForm
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.sql.Date
import java.sql.ResultSet
import java.sql.SQLException
import java.time.OffsetDateTime

class TodoRepositoryImpl : KoinComponent, TodoRepository {
    private val dbConnection: dbConnection by inject()

    override fun getTodoLists(): ResultSet? {
        try {
            val statement = dbConnection.connection?.createStatement()
            return statement?.executeQuery("select * from posts where delete_flg = false")
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
            val query =
                "insert into posts (title,target_complete_date) values (?, ?) "
            val preparedStatement =
                dbConnection.connection?.prepareStatement(query)
            preparedStatement?.setString(1, todoForm.title)
            preparedStatement?.setDate(2, Date.valueOf(todoForm.inputDate))
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
            val now = OffsetDateTime.now()
            val query =
                "update posts set title = (?) , target_complete_date = (?), update_date = (?) where post_id = (?)"
            val preparedStatement =
                dbConnection.connection?.prepareStatement(query)
            preparedStatement?.setString(1, editTodoForm.title)
            preparedStatement?.setDate(2, Date.valueOf(editTodoForm.inputDate))
            preparedStatement?.setString(3, now.toString())
            preparedStatement?.setLong(4, editTodoForm.id)
            preparedStatement?.executeUpdate()
        } catch (e: SQLException) {
            e.printStackTrace()
            throw e
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override fun deleteTodo(deleteTodoForm: DeleteTodoForm) {
        try {
            val query = "update posts set delete_flg = true where post_id = (?)"
            val preparedStatement =
                dbConnection.connection?.prepareStatement(query)
            preparedStatement?.setLong(1, deleteTodoForm.id)
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