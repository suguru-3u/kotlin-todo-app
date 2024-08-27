package org.example.domain.model

import java.time.LocalDate

data class Todo(
    val postId:Long,
    val title:String,
    val dbId:Long,
    val finishDate:LocalDate
){
    fun print(){
        println("ID:${postId}")
        println("Todo:${title}")
        println("Finish Date: ${finishDate}\n")
    }
}
