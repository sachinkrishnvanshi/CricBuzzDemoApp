package com.example.codelab5.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.codelab5.model.PlayerEntity

@Database(entities = [PlayerEntity::class], version = 1)
abstract class PlayerDataBase(): RoomDatabase(){
    abstract fun playerdao():PlayerDao
    companion object{
        @Volatile
        private var instance : PlayerDataBase? = null

        fun getDatabase(context: Context): PlayerDataBase {
            if (instance == null){
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PlayerDataBase::class.java,
                        "userDB"
                    ).build()
                }
            }
            return instance!!
        }
    }

}