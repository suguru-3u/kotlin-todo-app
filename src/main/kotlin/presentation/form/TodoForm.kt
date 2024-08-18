package org.example.presentation.form

import com.mysql.cj.xdevapi.Schema.Validation

data class TodoForm(val title: String, var validationResult: Boolean = false) {
    init {
        println("入力した内容")
        println(title)
        val res = validation(title)
        if (res) {
            println("不正な文字を発見しました")
            validationResult = true
        }
    }

    private fun validation(title: String): Boolean {
        val regex = Regex("<script>")
        return regex.containsMatchIn(title)
    }
}
