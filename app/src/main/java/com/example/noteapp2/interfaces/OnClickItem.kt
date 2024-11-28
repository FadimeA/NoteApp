package com.example.noteapp2.interfaces

import com.example.noteapp2.data.models.NoteModel

interface OnClickItem {
    fun onLongClick(noteModel: NoteModel)

    fun onClick(noteModel: NoteModel)
}