package com.Mark.news.commons.data.local


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserData::class], version = 3,exportSchema = false)
abstract class LoginDb : RoomDatabase() {
    abstract fun loginDao(): LoginDao

}
