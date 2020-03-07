package com.Mark.news.commons.data.local

import androidx.room.*


@Dao
interface LoginDao {
    @Query("SELECT * FROM userdata")
    fun getUserData(): UserData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userData: UserData):Long


}