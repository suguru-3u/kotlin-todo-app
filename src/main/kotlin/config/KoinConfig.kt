package org.example.config

import org.example.presentation.handler.SignInHandler
import org.example.service.TodoService
import org.example.service.SignService
import org.example.domain.repository.SignRepository
import org.example.infrastructure.database.repository.SignRepositoryImpl
import org.example.domain.repository.TodoRepository
import org.example.infrastructure.database.repository.TodoRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object KoinConfig {
    val koinPracticModeules = module {

        // config
        singleOf(::DBConnection)

        // Handler
        singleOf(::SignInHandler)

        // Service
        singleOf(::TodoService)
        singleOf(::SignService)

        // Repository
        single<TodoRepository> { TodoRepositoryImpl() }
        single<SignRepository> { SignRepositoryImpl() }
    }
}