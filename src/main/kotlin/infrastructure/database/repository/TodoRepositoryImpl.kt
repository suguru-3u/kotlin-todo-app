package org.example.infrastructure.database.repository

import org.example.config.dbConnection
import org.example.domain.model.Todo
import org.example.domain.repository.TodoRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.sql.SQLException

class TodoRepositoryImpl : KoinComponent,
    TodoRepository {
    private val dbConnection: dbConnection by inject()

    override fun getTodoLists(): MutableList<Todo> {
        val todoLists: MutableList<Todo> =
            mutableListOf()
        try {
            dbConnection.opean()
            val statement =
                dbConnection.connection?.createStatement()
            val result =
                statement?.executeQuery("select * from posts")
            if (result != null) {
                while (result.next()) {
                    todoLists.add(
                        Todo(
                            result.getLong(
                                1
                            ), result.getString(2)
                        )
                    )
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            dbConnection.close()
        }
        return todoLists
    }
}