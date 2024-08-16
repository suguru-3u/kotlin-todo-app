package org.example.config

import org.example.service.TodoService
import org.example.domain.repository.TodoRepository
import org.example.infrastructure.database.repository.TodoRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object KoinConfig {
    val koinPracticModeules = module {

        // config
        singleOf(::dbConnection)

        // Service
        singleOf(::TodoService)

        // Repository
        single<TodoRepository> { TodoRepositoryImpl() }


    }
}