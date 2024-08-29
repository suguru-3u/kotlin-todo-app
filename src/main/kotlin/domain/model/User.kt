package org.example.domain.model

data class User(val userId:Long){
    override fun toString(): String {
        return "ユーザー情報： $userId"
    }
}
