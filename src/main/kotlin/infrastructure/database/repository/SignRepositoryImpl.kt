package org.example.infrastructure.database.repository

import org.example.config.DBConnection
import org.example.domain.repository.SignRepository
import org.example.presentation.form.SignInForm
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.sql.ResultSet
import java.sql.SQLException

class SignRepositoryImpl : KoinComponent, SignRepository {

    private val dbConnection: DBConnection by inject()

    override fun signIn(signInForm: SignInForm): ResultSet? {
        return runCatching {
            val connection = dbConnection.connection
                ?: throw Exception("データベース接続が確立されていません。")

            val sql = "SELECT user_id FROM users WHERE email = ? AND password = ?"
            val preparedStatement = connection.prepareStatement(sql)
                ?: throw Exception("PreparedStatementの作成に失敗しました。")

            preparedStatement.setString(1, signInForm.userEmail)
            preparedStatement.setString(2, signInForm.userPassword)
            return preparedStatement.executeQuery()
        }.getOrElse { exception ->
            throw Exception(
                "ユーザー情報の取得に失敗しました： ${exception.message}",
                exception
            )
        }
    }
}