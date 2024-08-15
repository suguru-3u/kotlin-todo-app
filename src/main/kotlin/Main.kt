package org.example

import org.example.config.dbConnection
import java.util.Scanner

//TIP コードを<b>実行</b>するには、<shortcut actionId="Run"/> を押すか
// ガターの <icon src="AllIcons.Actions.Execute"/> アイコンをクリックします。
fun main() {
    println("Hello Kotlin TODO APP")

    var appEndFlg = false
    val dbConnection = dbConnection()
    dbConnection.connection()
    while (!appEndFlg) {
        System.out.println("TODOをどうしますか？")
        println("登録している一覧を確認するには、「１」、新規登録する場合は「２」、アプリを終了する場合は「３」を入力してください")
        val scan = Scanner(System.`in`)
        val selectCommand = scan.next()
        if (selectCommand.equals("1")) {
            println("一覧はこちらです")
        }
        // DBと接続するクラスを作成
        // アプリ起動時にDBと接続するようにする


        appEndFlg = true
    }
    dbConnection.close()
}