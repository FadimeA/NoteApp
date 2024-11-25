package com.example.noteapp2.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp2.data.db.daos.Dao
import com.example.noteapp2.data.models.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun Dao() : Dao
}