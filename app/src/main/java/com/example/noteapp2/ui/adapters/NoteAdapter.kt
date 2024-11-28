package com.example.noteapp2.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp2.data.models.NoteModel
import com.example.noteapp2.databinding.ItemNotesBinding
import com.example.noteapp2.interfaces.OnClickItem
import com.example.noteapp2.ui.fragments.note.NoteFragment


class NoteAdapter(
    private val onLongClick: OnClickItem,
    private val onClick: NoteFragment
) : ListAdapter<NoteModel, NoteAdapter.ViewHolder>(DiffcallBack()) {

    class ViewHolder(private val binding: ItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteModel?) {
            binding.txtTitle.text = item?.title
            binding.txtDescription.text = item?.description
            binding.txtDate.text = item?.date
            item?.color?.let { binding.itemBg.setBackgroundColor(it) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

        holder.itemView.setOnLongClickListener {
            onLongClick.onLongClick(getItem(position))
            true
        }
        holder.itemView.setOnClickListener {
            onClick.onClick(getItem(position))
        }
    }

    class DiffcallBack : DiffUtil.ItemCallback<NoteModel>() {
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }

    }
}