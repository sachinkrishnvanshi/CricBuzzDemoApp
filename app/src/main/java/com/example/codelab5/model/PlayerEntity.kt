package com.example.codelab5.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.codelab5.converter.Converter

@Entity(tableName = "player")

@TypeConverters(Converter::class)
data class PlayerEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val image:ByteArray? = byteArrayOf(),
    val favourite:Boolean,
    val name: String,
    val team:String,
    val country:String,
    val dob:String,
    val wicket:String,
    val run:String,
    val match:String,
    val radioBatsman: Boolean,
    val radioBowler: Boolean
)