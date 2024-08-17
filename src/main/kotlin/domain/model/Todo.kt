package org.example.domain.model

data class Todo(
    val postId:Long,
    val title:String,
){
    fun print(){
        println("ID:${postId}")
        println("Todo:${title}")
        println("")
    }
}
