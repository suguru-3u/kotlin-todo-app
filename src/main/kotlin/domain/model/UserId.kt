package org.example.domain.model

data class User(val userId:Long){
    override fun toString(): String {
        return "ユーザー情報： $userId"
    }
}

@JvmInline
value class UserId(val userId: Long){
    override fun toString(): String {
        return "ユーザー情報： $userId"
    }
}

