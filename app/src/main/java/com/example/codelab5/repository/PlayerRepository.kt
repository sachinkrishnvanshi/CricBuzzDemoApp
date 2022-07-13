package com.example.codelab5.repository

import androidx.lifecycle.LiveData
import com.example.codelab5.dataBase.PlayerDao
import com.example.codelab5.model.PlayerEntity

data class  PlayerRepository (val playerDao: PlayerDao) {
    val readAllData: LiveData<List<PlayerEntity>> = playerDao.getPlayer()
    suspend fun addPlayer(player: PlayerEntity){
        playerDao.insertPlayer(player)
    }
}