package org.example.config

import java.sql.Connection
import java.sql.DriverManager
import io.github.cdimascio.dotenv.dotenv

class dbConnection {
    private val url: String
    private val user: String
    private val password: String
    private var connection: Connection? = null

    init {
        println("DB初期化処理開始")
        val dotenv = dotenv()
        if (dotenv["LOCAL_DATABASE_NAME"].isNullOrBlank() || dotenv["LOCAL_USER_NAME"].isNullOrBlank() || dotenv["LOCAL_USER_PASSWORD"].isNullOrBlank()) {
            throw Error("DB接続用の環境変数が設定されていません")
        }
        this.url =
            "jdbc:mysql://localhost:3306/" + dotenv["LOCAL_DATABASE_NAME"]
        this.user = dotenv["LOCAL_USER_NAME"]
        this.password =
            dotenv["LOCAL_USER_PASSWORD"]
        println("DB初期化処理成功")
    }

    fun connection() {
        println("DB接続開始")
        try {
             this.connection =
                DriverManager.getConnection(
                    this.url,
                    this.user,
                    this.password
                )
            println("DB接続成功。")
        }catch (err: Exception) {
            println(err)
            println("DB接続処理に失敗しました")
        }
    }

    fun close(){
        println("DB接続解除開始")
        try {
            this.connection?.close()
            println("DB接続解除成功。")
        }catch (err: Exception) {
            println(err)
            println("DB接続解除処理に失敗しました")
        }
    }
}