package org.example.presentation.form

data class DeleteTodoForm(val id:Long){
    init {
        println("\n削除するTodoのId: $id\n")
    }
}
