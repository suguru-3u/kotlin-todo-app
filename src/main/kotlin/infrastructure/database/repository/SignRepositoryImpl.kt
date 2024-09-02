package org.example.infrastructure.database.repository

import org.example.config.DBConnection
import org.example.domain.repository.SignRepository
import org.example.presentation.form.SignInForm
import org.example.presentation.form.SignUpForm
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
            preparedStatement.executeQuery()
        }.getOrElse { exception ->
            throw Exception(
                "ユーザー情報の取得に失敗しました： ${exception.message}",
                exception
            )
        }
    }

    override fun signUp(signUpForm: SignUpForm) {
        try {
            val query =
                "insert into users (email,password) values (?, ?) "
            val preparedStatement =
                dbConnection.connection?.prepareStatement(query)
            preparedStatement?.setString(1, signUpForm.email)
            preparedStatement?.setString(2, signUpForm.password)
            val res= preparedStatement?.executeUpdate()
            println("実行結果： $res")
        } catch (e: SQLException) {
            e.printStackTrace()
            throw e
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}