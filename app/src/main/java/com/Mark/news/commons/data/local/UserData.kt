package com.Mark.news.commons.data.local

import androidx.room.Entity

import androidx.room.PrimaryKey

// operation type 1 for insert new post
// operation type 2 for update new post
// operation type 3 for delete new post
// operation type 0 for default
@Entity
data class UserData(
    @PrimaryKey(autoGenerate = true)  var empId: Int?,
    val token: String,
    val userName: String,
    val FullName: String,
    val Email: String,
    val expiresAt: String

)
