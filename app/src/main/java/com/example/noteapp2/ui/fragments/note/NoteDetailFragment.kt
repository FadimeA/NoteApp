package com.example.noteapp2.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.noteapp2.App
import com.example.noteapp2.R
import com.example.noteapp2.data.models.NoteModel
import com.example.noteapp2.databinding.FragmentNoteBinding
import com.example.noteapp2.databinding.FragmentNoteDetailBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding
    private val noteDate: String = SimpleDateFormat("dd MMMM", Locale.getDefault()).format(Date())
    private val noteTime: String = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
    }

    private fun setupListeners() {
        binding.tvDate.apply {
            binding.tvDate.text = noteDate
        }
        binding.tvTime.apply {
            binding.tvTime.text = noteTime
        }
    }

    private fun initialize() {
        binding.tvSave.setOnClickListener{
            val etTitle = binding.etTitle.text.toString().trim()
            val etDescription = binding.etDescription.text.toString().trim()

            App.appDataBase?.Dao()?.insert(NoteModel(etTitle, etDescription, noteTime, noteDate))
            findNavController().navigateUp()
        }
        binding.toolbarDetail.setNavigationOnClickListener{
            findNavController().navigateUp()
        }
    }

}