package com.example.codelab5.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.codelab5.model.PlayerEntity

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPlayer(user: PlayerEntity)

    @Update
    suspend fun updatePlayer(user: PlayerEntity)

    @Delete
    suspend fun deletePlayer(user: PlayerEntity)

    @Query("SELECT * FROM player")
    fun getPlayer() : LiveData<List<PlayerEntity>>
}