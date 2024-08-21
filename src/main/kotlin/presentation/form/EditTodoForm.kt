package org.example.presentation.form

data class EditTodoForm(
    val id: Long,
    val title: String,
    var valResult: Boolean = title.isBlank()
) {
    init {
        println("\n入力した内容: $title\n")
        if (valResult) println("空文字を検知しました")
    }
}