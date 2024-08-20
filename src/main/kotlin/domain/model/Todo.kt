package org.example.domain.model

data class Todo(
    val postId:Long,
    val title:String,
    val dbId:Long
){
    fun print(){
        println("ID:${postId}")
        println("Todo:${title}")
        println("Todo:${dbId}")
        println("")
    }
}
