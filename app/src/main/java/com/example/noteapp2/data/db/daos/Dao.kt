package com.example.noteapp2.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.noteapp2.data.models.NoteModel

@Dao
interface Dao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(noteModel: NoteModel)

    @Query("SELECT * FROM noteModel")
    fun getAll():LiveData<List<NoteModel>>
}