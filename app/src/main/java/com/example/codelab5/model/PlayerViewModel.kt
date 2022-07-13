package com.example.codelab5.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.codelab5.dataBase.PlayerDataBase
import com.example.codelab5.repository.PlayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayerViewModel (application: Application): AndroidViewModel(application){
    val readAllData: LiveData<List<PlayerEntity>>
    private val repository: PlayerRepository

    init {
        val playerDao= PlayerDataBase.getDatabase(application).playerdao()
        repository= PlayerRepository(playerDao)
        readAllData=repository.readAllData
    }
    fun addPlayer(player: PlayerEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPlayer(player)
        }
    }
}